package com.google.zxing;

import com.google.zxing.aztec.AztecWriter;
import com.google.zxing.common.BitMatrix;
import com.google.zxing.datamatrix.DataMatrixWriter;
import com.google.zxing.oned.CodaBarWriter;
import com.google.zxing.oned.Code128Writer;
import com.google.zxing.oned.Code39Writer;
import com.google.zxing.oned.Code93Writer;
import com.google.zxing.oned.EAN13Writer;
import com.google.zxing.oned.EAN8Writer;
import com.google.zxing.oned.ITFWriter;
import com.google.zxing.oned.UPCAWriter;
import com.google.zxing.oned.UPCEWriter;
import com.google.zxing.pdf417.PDF417Writer;
import com.google.zxing.qrcode.QRCodeWriter;
import java.util.Map;

public final class MultiFormatWriter implements Writer {
    public BitMatrix encode(String str, BarcodeFormat barcodeFormat, int i, int i2) {
        return encode(str, barcodeFormat, i, i2, (Map<EncodeHintType, ?>) null);
    }

    public BitMatrix encode(String str, BarcodeFormat barcodeFormat, int i, int i2, Map<EncodeHintType, ?> map) {
        Writer writer;
        switch (barcodeFormat) {
            case EAN_8:
                writer = new EAN8Writer();
                break;
            case UPC_E:
                writer = new UPCEWriter();
                break;
            case EAN_13:
                writer = new EAN13Writer();
                break;
            case UPC_A:
                writer = new UPCAWriter();
                break;
            case QR_CODE:
                writer = new QRCodeWriter();
                break;
            case CODE_39:
                writer = new Code39Writer();
                break;
            case CODE_93:
                writer = new Code93Writer();
                break;
            case CODE_128:
                writer = new Code128Writer();
                break;
            case ITF:
                writer = new ITFWriter();
                break;
            case PDF_417:
                writer = new PDF417Writer();
                break;
            case CODABAR:
                writer = new CodaBarWriter();
                break;
            case DATA_MATRIX:
                writer = new DataMatrixWriter();
                break;
            case AZTEC:
                writer = new AztecWriter();
                break;
            default:
                throw new IllegalArgumentException("No encoder available for format " + barcodeFormat);
        }
        return writer.encode(str, barcodeFormat, i, i2, map);
    }
}
