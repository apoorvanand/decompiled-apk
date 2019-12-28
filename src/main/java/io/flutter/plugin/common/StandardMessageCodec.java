package io.flutter.plugin.common;

import java.io.ByteArrayOutputStream;
import java.math.BigInteger;
import java.nio.ByteBuffer;
import java.nio.ByteOrder;
import java.nio.charset.Charset;
import java.util.List;
import java.util.Map;

public class StandardMessageCodec implements MessageCodec<Object> {
    private static final byte BIGINT = 5;
    private static final byte BYTE_ARRAY = 8;
    private static final byte DOUBLE = 6;
    private static final byte DOUBLE_ARRAY = 11;
    private static final byte FALSE = 2;
    public static final StandardMessageCodec INSTANCE = new StandardMessageCodec();
    private static final byte INT = 3;
    private static final byte INT_ARRAY = 9;
    private static final byte LIST = 12;
    private static final boolean LITTLE_ENDIAN = (ByteOrder.nativeOrder() == ByteOrder.LITTLE_ENDIAN);
    private static final byte LONG = 4;
    private static final byte LONG_ARRAY = 10;
    private static final byte MAP = 13;
    private static final byte NULL = 0;
    private static final byte STRING = 7;
    private static final String TAG = "StandardMessageCodec#";
    private static final byte TRUE = 1;
    private static final Charset UTF8 = Charset.forName("UTF8");

    static final class ExposedByteArrayOutputStream extends ByteArrayOutputStream {
        ExposedByteArrayOutputStream() {
        }

        /* access modifiers changed from: package-private */
        public byte[] buffer() {
            return this.buf;
        }
    }

    protected static final void readAlignment(ByteBuffer byteBuffer, int i) {
        int position = byteBuffer.position() % i;
        if (position != 0) {
            byteBuffer.position((byteBuffer.position() + i) - position);
        }
    }

    protected static final byte[] readBytes(ByteBuffer byteBuffer) {
        byte[] bArr = new byte[readSize(byteBuffer)];
        byteBuffer.get(bArr);
        return bArr;
    }

    protected static final int readSize(ByteBuffer byteBuffer) {
        if (byteBuffer.hasRemaining()) {
            byte b = byteBuffer.get() & 255;
            return b < 254 ? b : b == 254 ? byteBuffer.getChar() : byteBuffer.getInt();
        }
        throw new IllegalArgumentException("Message corrupted");
    }

    protected static final void writeAlignment(ByteArrayOutputStream byteArrayOutputStream, int i) {
        int size = byteArrayOutputStream.size() % i;
        if (size != 0) {
            for (int i2 = 0; i2 < i - size; i2++) {
                byteArrayOutputStream.write(0);
            }
        }
    }

    protected static final void writeBytes(ByteArrayOutputStream byteArrayOutputStream, byte[] bArr) {
        writeSize(byteArrayOutputStream, bArr.length);
        byteArrayOutputStream.write(bArr, 0, bArr.length);
    }

    protected static final void writeChar(ByteArrayOutputStream byteArrayOutputStream, int i) {
        if (LITTLE_ENDIAN) {
            byteArrayOutputStream.write(i);
            i >>>= 8;
        } else {
            byteArrayOutputStream.write(i >>> 8);
        }
        byteArrayOutputStream.write(i);
    }

    protected static final void writeDouble(ByteArrayOutputStream byteArrayOutputStream, double d) {
        writeLong(byteArrayOutputStream, Double.doubleToLongBits(d));
    }

    protected static final void writeInt(ByteArrayOutputStream byteArrayOutputStream, int i) {
        if (LITTLE_ENDIAN) {
            byteArrayOutputStream.write(i);
            byteArrayOutputStream.write(i >>> 8);
            byteArrayOutputStream.write(i >>> 16);
            i >>>= 24;
        } else {
            byteArrayOutputStream.write(i >>> 24);
            byteArrayOutputStream.write(i >>> 16);
            byteArrayOutputStream.write(i >>> 8);
        }
        byteArrayOutputStream.write(i);
    }

    protected static final void writeLong(ByteArrayOutputStream byteArrayOutputStream, long j) {
        if (LITTLE_ENDIAN) {
            byteArrayOutputStream.write((byte) ((int) j));
            byteArrayOutputStream.write((byte) ((int) (j >>> 8)));
            byteArrayOutputStream.write((byte) ((int) (j >>> 16)));
            byteArrayOutputStream.write((byte) ((int) (j >>> 24)));
            byteArrayOutputStream.write((byte) ((int) (j >>> 32)));
            byteArrayOutputStream.write((byte) ((int) (j >>> 40)));
            byteArrayOutputStream.write((byte) ((int) (j >>> 48)));
            j >>>= 56;
        } else {
            byteArrayOutputStream.write((byte) ((int) (j >>> 56)));
            byteArrayOutputStream.write((byte) ((int) (j >>> 48)));
            byteArrayOutputStream.write((byte) ((int) (j >>> 40)));
            byteArrayOutputStream.write((byte) ((int) (j >>> 32)));
            byteArrayOutputStream.write((byte) ((int) (j >>> 24)));
            byteArrayOutputStream.write((byte) ((int) (j >>> 16)));
            byteArrayOutputStream.write((byte) ((int) (j >>> 8)));
        }
        byteArrayOutputStream.write((byte) ((int) j));
    }

    protected static final void writeSize(ByteArrayOutputStream byteArrayOutputStream, int i) {
        if (i < 254) {
            byteArrayOutputStream.write(i);
        } else if (i <= 65535) {
            byteArrayOutputStream.write(254);
            writeChar(byteArrayOutputStream, i);
        } else {
            byteArrayOutputStream.write(255);
            writeInt(byteArrayOutputStream, i);
        }
    }

    public Object decodeMessage(ByteBuffer byteBuffer) {
        if (byteBuffer == null) {
            return null;
        }
        byteBuffer.order(ByteOrder.nativeOrder());
        Object readValue = readValue(byteBuffer);
        if (!byteBuffer.hasRemaining()) {
            return readValue;
        }
        throw new IllegalArgumentException("Message corrupted");
    }

    public ByteBuffer encodeMessage(Object obj) {
        if (obj == null) {
            return null;
        }
        ExposedByteArrayOutputStream exposedByteArrayOutputStream = new ExposedByteArrayOutputStream();
        writeValue(exposedByteArrayOutputStream, obj);
        ByteBuffer allocateDirect = ByteBuffer.allocateDirect(exposedByteArrayOutputStream.size());
        allocateDirect.put(exposedByteArrayOutputStream.buffer(), 0, exposedByteArrayOutputStream.size());
        return allocateDirect;
    }

    /* access modifiers changed from: protected */
    public final Object readValue(ByteBuffer byteBuffer) {
        if (byteBuffer.hasRemaining()) {
            return readValueOfType(byteBuffer.get(), byteBuffer);
        }
        throw new IllegalArgumentException("Message corrupted");
    }

    /* JADX DEBUG: Multi-variable search result rejected for TypeSearchVarInfo{r0v3, resolved type: long[]} */
    /* JADX DEBUG: Multi-variable search result rejected for TypeSearchVarInfo{r0v10, resolved type: long[]} */
    /* JADX DEBUG: Multi-variable search result rejected for TypeSearchVarInfo{r0v11, resolved type: long[]} */
    /* access modifiers changed from: protected */
    /* JADX WARNING: Multi-variable type inference failed */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    public java.lang.Object readValueOfType(byte r5, java.nio.ByteBuffer r6) {
        /*
            r4 = this;
            r0 = 0
            r1 = 8
            switch(r5) {
                case 0: goto L_0x00d2;
                case 1: goto L_0x00cc;
                case 2: goto L_0x00c7;
                case 3: goto L_0x00be;
                case 4: goto L_0x00b5;
                case 5: goto L_0x00a2;
                case 6: goto L_0x0096;
                case 7: goto L_0x008a;
                case 8: goto L_0x0085;
                case 9: goto L_0x0069;
                case 10: goto L_0x004d;
                case 11: goto L_0x003c;
                case 12: goto L_0x0027;
                case 13: goto L_0x000e;
                default: goto L_0x0006;
            }
        L_0x0006:
            java.lang.IllegalArgumentException r5 = new java.lang.IllegalArgumentException
            java.lang.String r6 = "Message corrupted"
            r5.<init>(r6)
            throw r5
        L_0x000e:
            int r5 = readSize(r6)
            java.util.HashMap r1 = new java.util.HashMap
            r1.<init>()
        L_0x0017:
            if (r0 >= r5) goto L_0x00d3
            java.lang.Object r2 = r4.readValue(r6)
            java.lang.Object r3 = r4.readValue(r6)
            r1.put(r2, r3)
            int r0 = r0 + 1
            goto L_0x0017
        L_0x0027:
            int r5 = readSize(r6)
            java.util.ArrayList r1 = new java.util.ArrayList
            r1.<init>(r5)
        L_0x0030:
            if (r0 >= r5) goto L_0x00d3
            java.lang.Object r2 = r4.readValue(r6)
            r1.add(r2)
            int r0 = r0 + 1
            goto L_0x0030
        L_0x003c:
            int r5 = readSize(r6)
            double[] r0 = new double[r5]
            readAlignment(r6, r1)
            java.nio.DoubleBuffer r2 = r6.asDoubleBuffer()
            r2.get(r0)
            goto L_0x005d
        L_0x004d:
            int r5 = readSize(r6)
            long[] r0 = new long[r5]
            readAlignment(r6, r1)
            java.nio.LongBuffer r2 = r6.asLongBuffer()
            r2.get(r0)
        L_0x005d:
            int r2 = r6.position()
            int r5 = r5 * 8
            int r2 = r2 + r5
            r6.position(r2)
            r1 = r0
            goto L_0x00d3
        L_0x0069:
            int r5 = readSize(r6)
            int[] r1 = new int[r5]
            r0 = 4
            readAlignment(r6, r0)
            java.nio.IntBuffer r2 = r6.asIntBuffer()
            r2.get(r1)
            int r2 = r6.position()
            int r5 = r5 * 4
            int r2 = r2 + r5
            r6.position(r2)
            goto L_0x00d3
        L_0x0085:
            byte[] r1 = readBytes(r6)
            goto L_0x00d3
        L_0x008a:
            byte[] r5 = readBytes(r6)
            java.lang.String r1 = new java.lang.String
            java.nio.charset.Charset r6 = UTF8
            r1.<init>(r5, r6)
            goto L_0x00d3
        L_0x0096:
            readAlignment(r6, r1)
            double r5 = r6.getDouble()
            java.lang.Double r1 = java.lang.Double.valueOf(r5)
            goto L_0x00d3
        L_0x00a2:
            byte[] r5 = readBytes(r6)
            java.math.BigInteger r1 = new java.math.BigInteger
            java.lang.String r6 = new java.lang.String
            java.nio.charset.Charset r0 = UTF8
            r6.<init>(r5, r0)
            r5 = 16
            r1.<init>(r6, r5)
            goto L_0x00d3
        L_0x00b5:
            long r5 = r6.getLong()
            java.lang.Long r1 = java.lang.Long.valueOf(r5)
            goto L_0x00d3
        L_0x00be:
            int r5 = r6.getInt()
            java.lang.Integer r1 = java.lang.Integer.valueOf(r5)
            goto L_0x00d3
        L_0x00c7:
            java.lang.Boolean r1 = java.lang.Boolean.valueOf(r0)
            goto L_0x00d3
        L_0x00cc:
            r5 = 1
            java.lang.Boolean r1 = java.lang.Boolean.valueOf(r5)
            goto L_0x00d3
        L_0x00d2:
            r1 = 0
        L_0x00d3:
            return r1
        */
        throw new UnsupportedOperationException("Method not decompiled: io.flutter.plugin.common.StandardMessageCodec.readValueOfType(byte, java.nio.ByteBuffer):java.lang.Object");
    }

    /* access modifiers changed from: protected */
    public void writeValue(ByteArrayOutputStream byteArrayOutputStream, Object obj) {
        byte[] bArr;
        String str;
        int i;
        int i2 = 0;
        if (obj == null) {
            byteArrayOutputStream.write(0);
            return;
        }
        if (obj == Boolean.TRUE) {
            i = 1;
        } else if (obj == Boolean.FALSE) {
            i = 2;
        } else {
            if (obj instanceof Number) {
                if ((obj instanceof Integer) || (obj instanceof Short) || (obj instanceof Byte)) {
                    byteArrayOutputStream.write(3);
                    writeInt(byteArrayOutputStream, ((Number) obj).intValue());
                    return;
                } else if (obj instanceof Long) {
                    byteArrayOutputStream.write(4);
                    writeLong(byteArrayOutputStream, ((Long) obj).longValue());
                    return;
                } else if ((obj instanceof Float) || (obj instanceof Double)) {
                    byteArrayOutputStream.write(6);
                    writeAlignment(byteArrayOutputStream, 8);
                    writeDouble(byteArrayOutputStream, ((Number) obj).doubleValue());
                    return;
                } else if (obj instanceof BigInteger) {
                    byteArrayOutputStream.write(5);
                    str = ((BigInteger) obj).toString(16);
                } else {
                    throw new IllegalArgumentException("Unsupported Number type: " + obj.getClass());
                }
            } else if (obj instanceof String) {
                byteArrayOutputStream.write(7);
                str = (String) obj;
            } else if (obj instanceof byte[]) {
                byteArrayOutputStream.write(8);
                bArr = (byte[]) obj;
                writeBytes(byteArrayOutputStream, bArr);
                return;
            } else if (obj instanceof int[]) {
                byteArrayOutputStream.write(9);
                int[] iArr = (int[]) obj;
                writeSize(byteArrayOutputStream, iArr.length);
                writeAlignment(byteArrayOutputStream, 4);
                int length = iArr.length;
                while (i2 < length) {
                    writeInt(byteArrayOutputStream, iArr[i2]);
                    i2++;
                }
                return;
            } else if (obj instanceof long[]) {
                byteArrayOutputStream.write(10);
                long[] jArr = (long[]) obj;
                writeSize(byteArrayOutputStream, jArr.length);
                writeAlignment(byteArrayOutputStream, 8);
                int length2 = jArr.length;
                while (i2 < length2) {
                    writeLong(byteArrayOutputStream, jArr[i2]);
                    i2++;
                }
                return;
            } else if (obj instanceof double[]) {
                byteArrayOutputStream.write(11);
                double[] dArr = (double[]) obj;
                writeSize(byteArrayOutputStream, dArr.length);
                writeAlignment(byteArrayOutputStream, 8);
                int length3 = dArr.length;
                while (i2 < length3) {
                    writeDouble(byteArrayOutputStream, dArr[i2]);
                    i2++;
                }
                return;
            } else if (obj instanceof List) {
                byteArrayOutputStream.write(12);
                List<Object> list = (List) obj;
                writeSize(byteArrayOutputStream, list.size());
                for (Object writeValue : list) {
                    writeValue(byteArrayOutputStream, writeValue);
                }
                return;
            } else if (obj instanceof Map) {
                byteArrayOutputStream.write(13);
                Map map = (Map) obj;
                writeSize(byteArrayOutputStream, map.size());
                for (Map.Entry entry : map.entrySet()) {
                    writeValue(byteArrayOutputStream, entry.getKey());
                    writeValue(byteArrayOutputStream, entry.getValue());
                }
                return;
            } else {
                throw new IllegalArgumentException("Unsupported value: " + obj);
            }
            bArr = str.getBytes(UTF8);
            writeBytes(byteArrayOutputStream, bArr);
            return;
        }
        byteArrayOutputStream.write(i);
    }
}
