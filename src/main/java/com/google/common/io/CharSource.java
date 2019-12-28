package com.google.common.io;

import com.google.common.annotations.Beta;
import com.google.common.annotations.GwtIncompatible;
import com.google.common.base.Ascii;
import com.google.common.base.Optional;
import com.google.common.base.Preconditions;
import com.google.common.base.Splitter;
import com.google.common.collect.AbstractIterator;
import com.google.common.collect.ImmutableList;
import com.google.common.collect.Lists;
import com.google.errorprone.annotations.CanIgnoreReturnValue;
import java.io.BufferedReader;
import java.io.InputStream;
import java.io.Reader;
import java.io.Writer;
import java.nio.charset.Charset;
import java.util.ArrayList;
import java.util.Iterator;
import javax.annotation.Nullable;
import kotlin.jvm.internal.LongCompanionObject;

@GwtIncompatible
public abstract class CharSource {

    private final class AsByteSource extends ByteSource {
        final Charset a;

        AsByteSource(Charset charset) {
            this.a = (Charset) Preconditions.checkNotNull(charset);
        }

        public CharSource asCharSource(Charset charset) {
            return charset.equals(this.a) ? CharSource.this : super.asCharSource(charset);
        }

        public InputStream openStream() {
            return new ReaderInputStream(CharSource.this.openStream(), this.a, 8192);
        }

        public String toString() {
            return CharSource.this.toString() + ".asByteSource(" + this.a + ")";
        }
    }

    private static class CharSequenceCharSource extends CharSource {
        /* access modifiers changed from: private */
        public static final Splitter LINE_SPLITTER = Splitter.onPattern("\r\n|\n|\r");
        /* access modifiers changed from: private */
        public final CharSequence seq;

        protected CharSequenceCharSource(CharSequence charSequence) {
            this.seq = (CharSequence) Preconditions.checkNotNull(charSequence);
        }

        private Iterable<String> lines() {
            return new Iterable<String>() {
                public Iterator<String> iterator() {
                    return new AbstractIterator<String>() {
                        Iterator<String> a = CharSequenceCharSource.LINE_SPLITTER.split(CharSequenceCharSource.this.seq).iterator();

                        /* access modifiers changed from: protected */
                        /* renamed from: b */
                        public String computeNext() {
                            if (this.a.hasNext()) {
                                String next = this.a.next();
                                if (this.a.hasNext() || !next.isEmpty()) {
                                    return next;
                                }
                            }
                            return (String) a();
                        }
                    };
                }
            };
        }

        public boolean isEmpty() {
            return this.seq.length() == 0;
        }

        public long length() {
            return (long) this.seq.length();
        }

        public Optional<Long> lengthIfKnown() {
            return Optional.of(Long.valueOf((long) this.seq.length()));
        }

        public Reader openStream() {
            return new CharSequenceReader(this.seq);
        }

        public String read() {
            return this.seq.toString();
        }

        public String readFirstLine() {
            Iterator<String> it = lines().iterator();
            if (it.hasNext()) {
                return it.next();
            }
            return null;
        }

        public ImmutableList<String> readLines() {
            return ImmutableList.copyOf(lines());
        }

        /* JADX WARNING: Removed duplicated region for block: B:1:0x0008 A[LOOP:0: B:1:0x0008->B:4:0x0018, LOOP_START] */
        /* Code decompiled incorrectly, please refer to instructions dump. */
        public <T> T readLines(com.google.common.io.LineProcessor<T> r3) {
            /*
                r2 = this;
                java.lang.Iterable r0 = r2.lines()
                java.util.Iterator r0 = r0.iterator()
            L_0x0008:
                boolean r1 = r0.hasNext()
                if (r1 == 0) goto L_0x001a
                java.lang.Object r1 = r0.next()
                java.lang.String r1 = (java.lang.String) r1
                boolean r1 = r3.processLine(r1)
                if (r1 != 0) goto L_0x0008
            L_0x001a:
                java.lang.Object r3 = r3.getResult()
                return r3
            */
            throw new UnsupportedOperationException("Method not decompiled: com.google.common.io.CharSource.CharSequenceCharSource.readLines(com.google.common.io.LineProcessor):java.lang.Object");
        }

        public String toString() {
            return "CharSource.wrap(" + Ascii.truncate(this.seq, 30, "...") + ")";
        }
    }

    private static final class ConcatenatedCharSource extends CharSource {
        private final Iterable<? extends CharSource> sources;

        ConcatenatedCharSource(Iterable<? extends CharSource> iterable) {
            this.sources = (Iterable) Preconditions.checkNotNull(iterable);
        }

        public boolean isEmpty() {
            for (CharSource isEmpty : this.sources) {
                if (!isEmpty.isEmpty()) {
                    return false;
                }
            }
            return true;
        }

        public long length() {
            long j = 0;
            for (CharSource length : this.sources) {
                j += length.length();
            }
            return j;
        }

        public Optional<Long> lengthIfKnown() {
            long j = 0;
            for (CharSource lengthIfKnown : this.sources) {
                Optional<Long> lengthIfKnown2 = lengthIfKnown.lengthIfKnown();
                if (!lengthIfKnown2.isPresent()) {
                    return Optional.absent();
                }
                j += lengthIfKnown2.get().longValue();
            }
            return Optional.of(Long.valueOf(j));
        }

        public Reader openStream() {
            return new MultiReader(this.sources.iterator());
        }

        public String toString() {
            return "CharSource.concat(" + this.sources + ")";
        }
    }

    private static final class EmptyCharSource extends CharSequenceCharSource {
        /* access modifiers changed from: private */
        public static final EmptyCharSource INSTANCE = new EmptyCharSource();

        private EmptyCharSource() {
            super("");
        }

        public String toString() {
            return "CharSource.empty()";
        }
    }

    protected CharSource() {
    }

    public static CharSource concat(Iterable<? extends CharSource> iterable) {
        return new ConcatenatedCharSource(iterable);
    }

    public static CharSource concat(Iterator<? extends CharSource> it) {
        return concat((Iterable<? extends CharSource>) ImmutableList.copyOf(it));
    }

    public static CharSource concat(CharSource... charSourceArr) {
        return concat((Iterable<? extends CharSource>) ImmutableList.copyOf((E[]) charSourceArr));
    }

    private long countBySkipping(Reader reader) {
        long j = 0;
        while (true) {
            long skip = reader.skip(LongCompanionObject.MAX_VALUE);
            if (skip == 0) {
                return j;
            }
            j += skip;
        }
    }

    public static CharSource empty() {
        return EmptyCharSource.INSTANCE;
    }

    public static CharSource wrap(CharSequence charSequence) {
        return new CharSequenceCharSource(charSequence);
    }

    @Beta
    public ByteSource asByteSource(Charset charset) {
        return new AsByteSource(charset);
    }

    @CanIgnoreReturnValue
    public long copyTo(CharSink charSink) {
        Preconditions.checkNotNull(charSink);
        Closer create = Closer.create();
        try {
            long copy = CharStreams.copy((Reader) create.register(openStream()), (Writer) create.register(charSink.openStream()));
            create.close();
            return copy;
        } catch (Throwable th) {
            create.close();
            throw th;
        }
    }

    @CanIgnoreReturnValue
    public long copyTo(Appendable appendable) {
        Preconditions.checkNotNull(appendable);
        Closer create = Closer.create();
        try {
            long copy = CharStreams.copy((Reader) create.register(openStream()), appendable);
            create.close();
            return copy;
        } catch (Throwable th) {
            create.close();
            throw th;
        }
    }

    public boolean isEmpty() {
        Optional<Long> lengthIfKnown = lengthIfKnown();
        boolean z = true;
        if (lengthIfKnown.isPresent() && lengthIfKnown.get().longValue() == 0) {
            return true;
        }
        Closer create = Closer.create();
        try {
            if (((Reader) create.register(openStream())).read() != -1) {
                z = false;
            }
            create.close();
            return z;
        } catch (Throwable th) {
            create.close();
            throw th;
        }
    }

    @Beta
    public long length() {
        Optional<Long> lengthIfKnown = lengthIfKnown();
        if (lengthIfKnown.isPresent()) {
            return lengthIfKnown.get().longValue();
        }
        Closer create = Closer.create();
        try {
            long countBySkipping = countBySkipping((Reader) create.register(openStream()));
            create.close();
            return countBySkipping;
        } catch (Throwable th) {
            create.close();
            throw th;
        }
    }

    @Beta
    public Optional<Long> lengthIfKnown() {
        return Optional.absent();
    }

    public BufferedReader openBufferedStream() {
        Reader openStream = openStream();
        return openStream instanceof BufferedReader ? (BufferedReader) openStream : new BufferedReader(openStream);
    }

    public abstract Reader openStream();

    public String read() {
        Closer create = Closer.create();
        try {
            String charStreams = CharStreams.toString((Reader) create.register(openStream()));
            create.close();
            return charStreams;
        } catch (Throwable th) {
            create.close();
            throw th;
        }
    }

    @Nullable
    public String readFirstLine() {
        Closer create = Closer.create();
        try {
            String readLine = ((BufferedReader) create.register(openBufferedStream())).readLine();
            create.close();
            return readLine;
        } catch (Throwable th) {
            create.close();
            throw th;
        }
    }

    public ImmutableList<String> readLines() {
        Closer create = Closer.create();
        try {
            BufferedReader bufferedReader = (BufferedReader) create.register(openBufferedStream());
            ArrayList newArrayList = Lists.newArrayList();
            while (true) {
                String readLine = bufferedReader.readLine();
                if (readLine != null) {
                    newArrayList.add(readLine);
                } else {
                    ImmutableList<String> copyOf = ImmutableList.copyOf(newArrayList);
                    create.close();
                    return copyOf;
                }
            }
        } catch (Throwable th) {
            create.close();
            throw th;
        }
    }

    @CanIgnoreReturnValue
    @Beta
    public <T> T readLines(LineProcessor<T> lineProcessor) {
        Preconditions.checkNotNull(lineProcessor);
        Closer create = Closer.create();
        try {
            T readLines = CharStreams.readLines((Reader) create.register(openStream()), lineProcessor);
            create.close();
            return readLines;
        } catch (Throwable th) {
            create.close();
            throw th;
        }
    }
}
