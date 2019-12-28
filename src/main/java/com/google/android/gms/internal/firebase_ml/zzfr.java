package com.google.android.gms.internal.firebase_ml;

import java.io.ByteArrayOutputStream;
import java.io.EOFException;
import java.io.InputStream;
import java.nio.charset.Charset;
import java.util.logging.Level;
import java.util.logging.Logger;
import java.util.zip.GZIPInputStream;

public final class zzfr {
    private final int statusCode;
    private final zzfn zztq;
    private int zzuo;
    private boolean zzup;
    private InputStream zzvb;
    private final String zzvc;
    private final String zzvd;
    private zzfx zzve;
    private final String zzvf;
    private final zzfo zzvg;
    private boolean zzvh;

    zzfr(zzfo zzfo, zzfx zzfx) {
        StringBuilder sb;
        this.zzvg = zzfo;
        this.zzuo = zzfo.zzem();
        this.zzup = zzfo.zzen();
        this.zzve = zzfx;
        this.zzvc = zzfx.getContentEncoding();
        int statusCode2 = zzfx.getStatusCode();
        boolean z = false;
        this.statusCode = statusCode2 < 0 ? 0 : statusCode2;
        String reasonPhrase = zzfx.getReasonPhrase();
        this.zzvf = reasonPhrase;
        Logger logger = zzfv.a;
        if (this.zzup && logger.isLoggable(Level.CONFIG)) {
            z = true;
        }
        zzfn zzfn = null;
        if (z) {
            sb = new StringBuilder();
            sb.append("-------------- RESPONSE --------------");
            sb.append(zzif.zzaai);
            String zzez = zzfx.zzez();
            if (zzez != null) {
                sb.append(zzez);
            } else {
                sb.append(this.statusCode);
                if (reasonPhrase != null) {
                    sb.append(' ');
                    sb.append(reasonPhrase);
                }
            }
            sb.append(zzif.zzaai);
        } else {
            sb = null;
        }
        zzfo.zzep().zza(zzfx, z ? sb : null);
        String contentType = zzfx.getContentType();
        contentType = contentType == null ? zzfo.zzep().getContentType() : contentType;
        this.zzvd = contentType;
        this.zztq = contentType != null ? new zzfn(contentType) : zzfn;
        if (z) {
            logger.logp(Level.CONFIG, "com.google.api.client.http.HttpResponse", "<init>", sb.toString());
        }
    }

    private final Charset zzew() {
        zzfn zzfn = this.zztq;
        return (zzfn == null || zzfn.zzei() == null) ? zzhi.ISO_8859_1 : this.zztq.zzei();
    }

    public final void disconnect() {
        ignore();
        this.zzve.disconnect();
    }

    public final InputStream getContent() {
        if (!this.zzvh) {
            zzhw content = this.zzve.getContent();
            if (content != null) {
                try {
                    String str = this.zzvc;
                    if (str != null && str.contains("gzip")) {
                        content = new GZIPInputStream(content);
                    }
                    Logger logger = zzfv.a;
                    if (this.zzup && logger.isLoggable(Level.CONFIG)) {
                        content = new zzhw(content, logger, Level.CONFIG, this.zzuo);
                    }
                    this.zzvb = content;
                } catch (EOFException unused) {
                    content.close();
                } catch (Throwable th) {
                    content.close();
                    throw th;
                }
            }
            this.zzvh = true;
        }
        return this.zzvb;
    }

    public final String getContentType() {
        return this.zzvd;
    }

    public final int getStatusCode() {
        return this.statusCode;
    }

    public final String getStatusMessage() {
        return this.zzvf;
    }

    public final void ignore() {
        InputStream content = getContent();
        if (content != null) {
            content.close();
        }
    }

    public final <T> T zzb(Class<T> cls) {
        int i = this.statusCode;
        boolean z = true;
        if (this.zzvg.getRequestMethod().equals("HEAD") || i / 100 == 1 || i == 204 || i == 304) {
            ignore();
            z = false;
        }
        if (!z) {
            return null;
        }
        return this.zzvg.zzer().zza(getContent(), zzew(), cls);
    }

    public final zzfl zzeo() {
        return this.zzvg.zzep();
    }

    public final boolean zzeu() {
        int i = this.statusCode;
        return i >= 200 && i < 300;
    }

    /* JADX INFO: finally extract failed */
    public final String zzev() {
        InputStream content = getContent();
        if (content == null) {
            return "";
        }
        ByteArrayOutputStream byteArrayOutputStream = new ByteArrayOutputStream();
        try {
            zzky.checkNotNull(content);
            zzky.checkNotNull(byteArrayOutputStream);
            byte[] bArr = new byte[4096];
            while (true) {
                int read = content.read(bArr);
                if (read != -1) {
                    byteArrayOutputStream.write(bArr, 0, read);
                } else {
                    content.close();
                    return byteArrayOutputStream.toString(zzew().name());
                }
            }
        } catch (Throwable th) {
            content.close();
            throw th;
        }
    }
}
