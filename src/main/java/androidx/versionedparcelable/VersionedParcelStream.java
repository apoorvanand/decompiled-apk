package androidx.versionedparcelable;

import android.os.Bundle;
import android.os.IBinder;
import android.os.IInterface;
import android.os.Parcelable;
import android.util.SparseArray;
import androidx.annotation.RestrictTo;
import androidx.versionedparcelable.VersionedParcel;
import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.nio.charset.Charset;
import java.util.Set;

@RestrictTo({RestrictTo.Scope.LIBRARY})
class VersionedParcelStream extends VersionedParcel {
    private static final int TYPE_BOOLEAN = 5;
    private static final int TYPE_BOOLEAN_ARRAY = 6;
    private static final int TYPE_DOUBLE = 7;
    private static final int TYPE_DOUBLE_ARRAY = 8;
    private static final int TYPE_FLOAT = 13;
    private static final int TYPE_FLOAT_ARRAY = 14;
    private static final int TYPE_INT = 9;
    private static final int TYPE_INT_ARRAY = 10;
    private static final int TYPE_LONG = 11;
    private static final int TYPE_LONG_ARRAY = 12;
    private static final int TYPE_NULL = 0;
    private static final int TYPE_STRING = 3;
    private static final int TYPE_STRING_ARRAY = 4;
    private static final int TYPE_SUB_BUNDLE = 1;
    private static final int TYPE_SUB_PERSISTABLE_BUNDLE = 2;
    private static final Charset UTF_16 = Charset.forName("UTF-16");
    private final SparseArray<InputBuffer> mCachedFields = new SparseArray<>();
    private DataInputStream mCurrentInput;
    private DataOutputStream mCurrentOutput;
    private FieldBuffer mFieldBuffer;
    private boolean mIgnoreParcelables;
    private final DataInputStream mMasterInput;
    private final DataOutputStream mMasterOutput;

    private static class FieldBuffer {
        final ByteArrayOutputStream a = new ByteArrayOutputStream();
        final DataOutputStream b = new DataOutputStream(this.a);
        private final int mFieldId;
        private final DataOutputStream mTarget;

        FieldBuffer(int i, DataOutputStream dataOutputStream) {
            this.mFieldId = i;
            this.mTarget = dataOutputStream;
        }

        /* access modifiers changed from: package-private */
        public void a() {
            this.b.flush();
            int size = this.a.size();
            this.mTarget.writeInt((this.mFieldId << 16) | (size >= 65535 ? 65535 : size));
            if (size >= 65535) {
                this.mTarget.writeInt(size);
            }
            this.a.writeTo(this.mTarget);
        }
    }

    private static class InputBuffer {
        final DataInputStream a;
        final int b;
        private final int mSize;

        InputBuffer(int i, int i2, DataInputStream dataInputStream) {
            this.mSize = i2;
            this.b = i;
            byte[] bArr = new byte[this.mSize];
            dataInputStream.readFully(bArr);
            this.a = new DataInputStream(new ByteArrayInputStream(bArr));
        }
    }

    public VersionedParcelStream(InputStream inputStream, OutputStream outputStream) {
        DataOutputStream dataOutputStream = null;
        this.mMasterInput = inputStream != null ? new DataInputStream(inputStream) : null;
        this.mMasterOutput = outputStream != null ? new DataOutputStream(outputStream) : dataOutputStream;
        this.mCurrentInput = this.mMasterInput;
        this.mCurrentOutput = this.mMasterOutput;
    }

    private void readObject(int i, String str, Bundle bundle) {
        switch (i) {
            case 0:
                bundle.putParcelable(str, (Parcelable) null);
                return;
            case 1:
            case 2:
                bundle.putBundle(str, readBundle());
                return;
            case 3:
                bundle.putString(str, readString());
                return;
            case 4:
                bundle.putStringArray(str, (String[]) b(new String[0]));
                return;
            case 5:
                bundle.putBoolean(str, readBoolean());
                return;
            case 6:
                bundle.putBooleanArray(str, b());
                return;
            case 7:
                bundle.putDouble(str, readDouble());
                return;
            case 8:
                bundle.putDoubleArray(str, f());
                return;
            case 9:
                bundle.putInt(str, readInt());
                return;
            case 10:
                bundle.putIntArray(str, c());
                return;
            case 11:
                bundle.putLong(str, readLong());
                return;
            case 12:
                bundle.putLongArray(str, d());
                return;
            case 13:
                bundle.putFloat(str, readFloat());
                return;
            case 14:
                bundle.putFloatArray(str, e());
                return;
            default:
                throw new RuntimeException("Unknown type " + i);
        }
    }

    private void writeObject(Object obj) {
        int intValue;
        if (obj == null) {
            intValue = 0;
        } else if (obj instanceof Bundle) {
            writeInt(1);
            writeBundle((Bundle) obj);
            return;
        } else if (obj instanceof String) {
            writeInt(3);
            writeString((String) obj);
            return;
        } else if (obj instanceof String[]) {
            writeInt(4);
            a((T[]) (String[]) obj);
            return;
        } else if (obj instanceof Boolean) {
            writeInt(5);
            writeBoolean(((Boolean) obj).booleanValue());
            return;
        } else if (obj instanceof boolean[]) {
            writeInt(6);
            a((boolean[]) obj);
            return;
        } else if (obj instanceof Double) {
            writeInt(7);
            writeDouble(((Double) obj).doubleValue());
            return;
        } else if (obj instanceof double[]) {
            writeInt(8);
            a((double[]) obj);
            return;
        } else if (obj instanceof Integer) {
            writeInt(9);
            intValue = ((Integer) obj).intValue();
        } else if (obj instanceof int[]) {
            writeInt(10);
            a((int[]) obj);
            return;
        } else if (obj instanceof Long) {
            writeInt(11);
            writeLong(((Long) obj).longValue());
            return;
        } else if (obj instanceof long[]) {
            writeInt(12);
            a((long[]) obj);
            return;
        } else if (obj instanceof Float) {
            writeInt(13);
            writeFloat(((Float) obj).floatValue());
            return;
        } else if (obj instanceof float[]) {
            writeInt(14);
            a((float[]) obj);
            return;
        } else {
            throw new IllegalArgumentException("Unsupported type " + obj.getClass());
        }
        writeInt(intValue);
    }

    /* access modifiers changed from: protected */
    public VersionedParcel a() {
        return new VersionedParcelStream(this.mCurrentInput, this.mCurrentOutput);
    }

    public void closeField() {
        FieldBuffer fieldBuffer = this.mFieldBuffer;
        if (fieldBuffer != null) {
            try {
                if (fieldBuffer.a.size() != 0) {
                    this.mFieldBuffer.a();
                }
                this.mFieldBuffer = null;
            } catch (IOException e) {
                throw new VersionedParcel.ParcelException(e);
            }
        }
    }

    public boolean isStream() {
        return true;
    }

    public boolean readBoolean() {
        try {
            return this.mCurrentInput.readBoolean();
        } catch (IOException e) {
            throw new VersionedParcel.ParcelException(e);
        }
    }

    public Bundle readBundle() {
        int readInt = readInt();
        if (readInt < 0) {
            return null;
        }
        Bundle bundle = new Bundle();
        for (int i = 0; i < readInt; i++) {
            readObject(readInt(), readString(), bundle);
        }
        return bundle;
    }

    public byte[] readByteArray() {
        try {
            int readInt = this.mCurrentInput.readInt();
            if (readInt <= 0) {
                return null;
            }
            byte[] bArr = new byte[readInt];
            this.mCurrentInput.readFully(bArr);
            return bArr;
        } catch (IOException e) {
            throw new VersionedParcel.ParcelException(e);
        }
    }

    public double readDouble() {
        try {
            return this.mCurrentInput.readDouble();
        } catch (IOException e) {
            throw new VersionedParcel.ParcelException(e);
        }
    }

    public boolean readField(int i) {
        InputBuffer inputBuffer = this.mCachedFields.get(i);
        if (inputBuffer != null) {
            this.mCachedFields.remove(i);
            this.mCurrentInput = inputBuffer.a;
            return true;
        }
        while (true) {
            try {
                int readInt = this.mMasterInput.readInt();
                int i2 = readInt & 65535;
                if (i2 == 65535) {
                    i2 = this.mMasterInput.readInt();
                }
                InputBuffer inputBuffer2 = new InputBuffer((readInt >> 16) & 65535, i2, this.mMasterInput);
                if (inputBuffer2.b == i) {
                    this.mCurrentInput = inputBuffer2.a;
                    return true;
                }
                this.mCachedFields.put(inputBuffer2.b, inputBuffer2);
            } catch (IOException unused) {
                return false;
            }
        }
    }

    public float readFloat() {
        try {
            return this.mCurrentInput.readFloat();
        } catch (IOException e) {
            throw new VersionedParcel.ParcelException(e);
        }
    }

    public int readInt() {
        try {
            return this.mCurrentInput.readInt();
        } catch (IOException e) {
            throw new VersionedParcel.ParcelException(e);
        }
    }

    public long readLong() {
        try {
            return this.mCurrentInput.readLong();
        } catch (IOException e) {
            throw new VersionedParcel.ParcelException(e);
        }
    }

    public <T extends Parcelable> T readParcelable() {
        return null;
    }

    public String readString() {
        try {
            int readInt = this.mCurrentInput.readInt();
            if (readInt <= 0) {
                return null;
            }
            byte[] bArr = new byte[readInt];
            this.mCurrentInput.readFully(bArr);
            return new String(bArr, UTF_16);
        } catch (IOException e) {
            throw new VersionedParcel.ParcelException(e);
        }
    }

    public IBinder readStrongBinder() {
        return null;
    }

    public void setOutputField(int i) {
        closeField();
        this.mFieldBuffer = new FieldBuffer(i, this.mMasterOutput);
        this.mCurrentOutput = this.mFieldBuffer.b;
    }

    public void setSerializationFlags(boolean z, boolean z2) {
        if (z) {
            this.mIgnoreParcelables = z2;
            return;
        }
        throw new RuntimeException("Serialization of this object is not allowed");
    }

    public void writeBoolean(boolean z) {
        try {
            this.mCurrentOutput.writeBoolean(z);
        } catch (IOException e) {
            throw new VersionedParcel.ParcelException(e);
        }
    }

    public void writeBundle(Bundle bundle) {
        if (bundle != null) {
            try {
                Set<String> keySet = bundle.keySet();
                this.mCurrentOutput.writeInt(keySet.size());
                for (String str : keySet) {
                    writeString(str);
                    writeObject(bundle.get(str));
                }
            } catch (IOException e) {
                throw new VersionedParcel.ParcelException(e);
            }
        } else {
            this.mCurrentOutput.writeInt(-1);
        }
    }

    public void writeByteArray(byte[] bArr) {
        if (bArr != null) {
            try {
                this.mCurrentOutput.writeInt(bArr.length);
                this.mCurrentOutput.write(bArr);
            } catch (IOException e) {
                throw new VersionedParcel.ParcelException(e);
            }
        } else {
            this.mCurrentOutput.writeInt(-1);
        }
    }

    public void writeByteArray(byte[] bArr, int i, int i2) {
        if (bArr != null) {
            try {
                this.mCurrentOutput.writeInt(i2);
                this.mCurrentOutput.write(bArr, i, i2);
            } catch (IOException e) {
                throw new VersionedParcel.ParcelException(e);
            }
        } else {
            this.mCurrentOutput.writeInt(-1);
        }
    }

    public void writeDouble(double d) {
        try {
            this.mCurrentOutput.writeDouble(d);
        } catch (IOException e) {
            throw new VersionedParcel.ParcelException(e);
        }
    }

    public void writeFloat(float f) {
        try {
            this.mCurrentOutput.writeFloat(f);
        } catch (IOException e) {
            throw new VersionedParcel.ParcelException(e);
        }
    }

    public void writeInt(int i) {
        try {
            this.mCurrentOutput.writeInt(i);
        } catch (IOException e) {
            throw new VersionedParcel.ParcelException(e);
        }
    }

    public void writeLong(long j) {
        try {
            this.mCurrentOutput.writeLong(j);
        } catch (IOException e) {
            throw new VersionedParcel.ParcelException(e);
        }
    }

    public void writeParcelable(Parcelable parcelable) {
        if (!this.mIgnoreParcelables) {
            throw new RuntimeException("Parcelables cannot be written to an OutputStream");
        }
    }

    public void writeString(String str) {
        if (str != null) {
            try {
                byte[] bytes = str.getBytes(UTF_16);
                this.mCurrentOutput.writeInt(bytes.length);
                this.mCurrentOutput.write(bytes);
            } catch (IOException e) {
                throw new VersionedParcel.ParcelException(e);
            }
        } else {
            this.mCurrentOutput.writeInt(-1);
        }
    }

    public void writeStrongBinder(IBinder iBinder) {
        if (!this.mIgnoreParcelables) {
            throw new RuntimeException("Binders cannot be written to an OutputStream");
        }
    }

    public void writeStrongInterface(IInterface iInterface) {
        if (!this.mIgnoreParcelables) {
            throw new RuntimeException("Binders cannot be written to an OutputStream");
        }
    }
}
