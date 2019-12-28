package com.google.firebase.ml.vision.barcode;

import android.graphics.Point;
import android.graphics.Rect;
import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import com.google.android.gms.common.internal.Preconditions;
import com.google.android.gms.internal.firebase_ml.zzlo;
import com.google.android.gms.vision.barcode.Barcode;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class FirebaseVisionBarcode {
    public static final int FORMAT_ALL_FORMATS = 0;
    public static final int FORMAT_AZTEC = 4096;
    public static final int FORMAT_CODABAR = 8;
    public static final int FORMAT_CODE_128 = 1;
    public static final int FORMAT_CODE_39 = 2;
    public static final int FORMAT_CODE_93 = 4;
    public static final int FORMAT_DATA_MATRIX = 16;
    public static final int FORMAT_EAN_13 = 32;
    public static final int FORMAT_EAN_8 = 64;
    public static final int FORMAT_ITF = 128;
    public static final int FORMAT_PDF417 = 2048;
    public static final int FORMAT_QR_CODE = 256;
    public static final int FORMAT_UNKNOWN = -1;
    public static final int FORMAT_UPC_A = 512;
    public static final int FORMAT_UPC_E = 1024;
    public static final int TYPE_CALENDAR_EVENT = 11;
    public static final int TYPE_CONTACT_INFO = 1;
    public static final int TYPE_DRIVER_LICENSE = 12;
    public static final int TYPE_EMAIL = 2;
    public static final int TYPE_GEO = 10;
    public static final int TYPE_ISBN = 3;
    public static final int TYPE_PHONE = 4;
    public static final int TYPE_PRODUCT = 5;
    public static final int TYPE_SMS = 6;
    public static final int TYPE_TEXT = 7;
    public static final int TYPE_UNKNOWN = 0;
    public static final int TYPE_URL = 8;
    public static final int TYPE_WIFI = 9;
    private static final Map<Integer, zzlo.zzv.zza> zzatj = new HashMap();
    private static final Map<Integer, zzlo.zzv.zzb> zzatk = new HashMap();
    private final Barcode zzatl;

    public static class Address {
        public static final int TYPE_HOME = 2;
        public static final int TYPE_UNKNOWN = 0;
        public static final int TYPE_WORK = 1;
        private final Barcode.Address zzatm;

        public @interface AddressType {
        }

        Address(@NonNull Barcode.Address address) {
            this.zzatm = (Barcode.Address) Preconditions.checkNotNull(address);
        }

        @NonNull
        public String[] getAddressLines() {
            return this.zzatm.addressLines;
        }

        @AddressType
        public int getType() {
            return this.zzatm.type;
        }
    }

    public @interface BarcodeFormat {
    }

    public @interface BarcodeValueType {
    }

    public static class CalendarDateTime {
        private final Barcode.CalendarDateTime zzatn;

        CalendarDateTime(@NonNull Barcode.CalendarDateTime calendarDateTime) {
            this.zzatn = calendarDateTime;
        }

        public int getDay() {
            return this.zzatn.day;
        }

        public int getHours() {
            return this.zzatn.hours;
        }

        public int getMinutes() {
            return this.zzatn.minutes;
        }

        public int getMonth() {
            return this.zzatn.month;
        }

        @Nullable
        public String getRawValue() {
            return this.zzatn.rawValue;
        }

        public int getSeconds() {
            return this.zzatn.seconds;
        }

        public int getYear() {
            return this.zzatn.year;
        }

        public boolean isUtc() {
            return this.zzatn.isUtc;
        }
    }

    public static class CalendarEvent {
        private final Barcode.CalendarEvent calendarEvent;

        CalendarEvent(@NonNull Barcode.CalendarEvent calendarEvent2) {
            this.calendarEvent = (Barcode.CalendarEvent) Preconditions.checkNotNull(calendarEvent2);
        }

        @Nullable
        public String getDescription() {
            return this.calendarEvent.description;
        }

        @Nullable
        public CalendarDateTime getEnd() {
            if (this.calendarEvent.end == null) {
                return null;
            }
            return new CalendarDateTime(this.calendarEvent.end);
        }

        @Nullable
        public String getLocation() {
            return this.calendarEvent.location;
        }

        @Nullable
        public String getOrganizer() {
            return this.calendarEvent.organizer;
        }

        @Nullable
        public CalendarDateTime getStart() {
            if (this.calendarEvent.start == null) {
                return null;
            }
            return new CalendarDateTime(this.calendarEvent.start);
        }

        @Nullable
        public String getStatus() {
            return this.calendarEvent.status;
        }

        @Nullable
        public String getSummary() {
            return this.calendarEvent.summary;
        }
    }

    public static class ContactInfo {
        private final Barcode.ContactInfo contactInfo;

        ContactInfo(@NonNull Barcode.ContactInfo contactInfo2) {
            this.contactInfo = (Barcode.ContactInfo) Preconditions.checkNotNull(contactInfo2);
        }

        public List<Address> getAddresses() {
            ArrayList arrayList = new ArrayList();
            if (this.contactInfo.addresses == null) {
                return arrayList;
            }
            for (Barcode.Address address : this.contactInfo.addresses) {
                if (address != null) {
                    arrayList.add(new Address(address));
                }
            }
            return arrayList;
        }

        public List<Email> getEmails() {
            ArrayList arrayList = new ArrayList();
            if (this.contactInfo.emails == null) {
                return arrayList;
            }
            for (Barcode.Email email : this.contactInfo.emails) {
                if (email != null) {
                    arrayList.add(new Email(email));
                }
            }
            return arrayList;
        }

        @Nullable
        public PersonName getName() {
            if (this.contactInfo.name == null) {
                return null;
            }
            return new PersonName(this.contactInfo.name);
        }

        @Nullable
        public String getOrganization() {
            return this.contactInfo.organization;
        }

        public List<Phone> getPhones() {
            ArrayList arrayList = new ArrayList();
            if (this.contactInfo.phones == null) {
                return arrayList;
            }
            for (Barcode.Phone phone : this.contactInfo.phones) {
                if (phone != null) {
                    arrayList.add(new Phone(phone));
                }
            }
            return arrayList;
        }

        @Nullable
        public String getTitle() {
            return this.contactInfo.title;
        }

        @Nullable
        public String[] getUrls() {
            return this.contactInfo.urls;
        }
    }

    public static class DriverLicense {
        private Barcode.DriverLicense driverLicense;

        DriverLicense(@NonNull Barcode.DriverLicense driverLicense2) {
            this.driverLicense = (Barcode.DriverLicense) Preconditions.checkNotNull(driverLicense2);
        }

        @Nullable
        public String getAddressCity() {
            return this.driverLicense.addressCity;
        }

        @Nullable
        public String getAddressState() {
            return this.driverLicense.addressState;
        }

        @Nullable
        public String getAddressStreet() {
            return this.driverLicense.addressStreet;
        }

        @Nullable
        public String getAddressZip() {
            return this.driverLicense.addressZip;
        }

        @Nullable
        public String getBirthDate() {
            return this.driverLicense.birthDate;
        }

        @Nullable
        public String getDocumentType() {
            return this.driverLicense.documentType;
        }

        @Nullable
        public String getExpiryDate() {
            return this.driverLicense.expiryDate;
        }

        @Nullable
        public String getFirstName() {
            return this.driverLicense.firstName;
        }

        @Nullable
        public String getGender() {
            return this.driverLicense.gender;
        }

        @Nullable
        public String getIssueDate() {
            return this.driverLicense.issueDate;
        }

        @Nullable
        public String getIssuingCountry() {
            return this.driverLicense.issuingCountry;
        }

        @Nullable
        public String getLastName() {
            return this.driverLicense.lastName;
        }

        @Nullable
        public String getLicenseNumber() {
            return this.driverLicense.licenseNumber;
        }

        @Nullable
        public String getMiddleName() {
            return this.driverLicense.middleName;
        }
    }

    public static class Email {
        public static final int TYPE_HOME = 2;
        public static final int TYPE_UNKNOWN = 0;
        public static final int TYPE_WORK = 1;
        private final Barcode.Email email;

        public @interface FormatType {
        }

        Email(@NonNull Barcode.Email email2) {
            this.email = (Barcode.Email) Preconditions.checkNotNull(email2);
        }

        @Nullable
        public String getAddress() {
            return this.email.address;
        }

        @Nullable
        public String getBody() {
            return this.email.body;
        }

        @Nullable
        public String getSubject() {
            return this.email.subject;
        }

        @FormatType
        public int getType() {
            return this.email.type;
        }
    }

    public static class GeoPoint {
        private final Barcode.GeoPoint geoPoint;

        GeoPoint(@NonNull Barcode.GeoPoint geoPoint2) {
            this.geoPoint = (Barcode.GeoPoint) Preconditions.checkNotNull(geoPoint2);
        }

        public double getLat() {
            return this.geoPoint.lat;
        }

        public double getLng() {
            return this.geoPoint.lng;
        }
    }

    public static class PersonName {
        private final Barcode.PersonName zzato;

        PersonName(@NonNull Barcode.PersonName personName) {
            this.zzato = (Barcode.PersonName) Preconditions.checkNotNull(personName);
        }

        @Nullable
        public String getFirst() {
            return this.zzato.first;
        }

        @Nullable
        public String getFormattedName() {
            return this.zzato.formattedName;
        }

        @Nullable
        public String getLast() {
            return this.zzato.last;
        }

        @Nullable
        public String getMiddle() {
            return this.zzato.middle;
        }

        @Nullable
        public String getPrefix() {
            return this.zzato.prefix;
        }

        @Nullable
        public String getPronunciation() {
            return this.zzato.pronunciation;
        }

        @Nullable
        public String getSuffix() {
            return this.zzato.suffix;
        }
    }

    public static class Phone {
        public static final int TYPE_FAX = 3;
        public static final int TYPE_HOME = 2;
        public static final int TYPE_MOBILE = 4;
        public static final int TYPE_UNKNOWN = 0;
        public static final int TYPE_WORK = 1;
        private final Barcode.Phone phone;

        public @interface FormatType {
        }

        Phone(@NonNull Barcode.Phone phone2) {
            this.phone = (Barcode.Phone) Preconditions.checkNotNull(phone2);
        }

        @Nullable
        public String getNumber() {
            return this.phone.number;
        }

        @FormatType
        public int getType() {
            return this.phone.type;
        }
    }

    public static class Sms {
        private final Barcode.Sms sms;

        Sms(@NonNull Barcode.Sms sms2) {
            this.sms = (Barcode.Sms) Preconditions.checkNotNull(sms2);
        }

        @Nullable
        public String getMessage() {
            return this.sms.message;
        }

        @Nullable
        public String getPhoneNumber() {
            return this.sms.phoneNumber;
        }
    }

    public static class UrlBookmark {
        private final Barcode.UrlBookmark zzatp;

        UrlBookmark(@NonNull Barcode.UrlBookmark urlBookmark) {
            this.zzatp = (Barcode.UrlBookmark) Preconditions.checkNotNull(urlBookmark);
        }

        @Nullable
        public String getTitle() {
            return this.zzatp.title;
        }

        @Nullable
        public String getUrl() {
            return this.zzatp.url;
        }
    }

    public static class WiFi {
        public static final int TYPE_OPEN = 1;
        public static final int TYPE_WEP = 3;
        public static final int TYPE_WPA = 2;
        private final Barcode.WiFi zzatq;

        public @interface EncryptionType {
        }

        WiFi(@NonNull Barcode.WiFi wiFi) {
            this.zzatq = (Barcode.WiFi) Preconditions.checkNotNull(wiFi);
        }

        @EncryptionType
        public int getEncryptionType() {
            return this.zzatq.encryptionType;
        }

        @Nullable
        public String getPassword() {
            return this.zzatq.password;
        }

        @Nullable
        public String getSsid() {
            return this.zzatq.ssid;
        }
    }

    static {
        zzatj.put(-1, zzlo.zzv.zza.FORMAT_UNKNOWN);
        zzatj.put(1, zzlo.zzv.zza.FORMAT_CODE_128);
        zzatj.put(2, zzlo.zzv.zza.FORMAT_CODE_39);
        zzatj.put(4, zzlo.zzv.zza.FORMAT_CODE_93);
        zzatj.put(8, zzlo.zzv.zza.FORMAT_CODABAR);
        zzatj.put(16, zzlo.zzv.zza.FORMAT_DATA_MATRIX);
        zzatj.put(32, zzlo.zzv.zza.FORMAT_EAN_13);
        zzatj.put(64, zzlo.zzv.zza.FORMAT_EAN_8);
        zzatj.put(128, zzlo.zzv.zza.FORMAT_ITF);
        zzatj.put(256, zzlo.zzv.zza.FORMAT_QR_CODE);
        zzatj.put(512, zzlo.zzv.zza.FORMAT_UPC_A);
        zzatj.put(1024, zzlo.zzv.zza.FORMAT_UPC_E);
        zzatj.put(2048, zzlo.zzv.zza.FORMAT_PDF417);
        zzatj.put(4096, zzlo.zzv.zza.FORMAT_AZTEC);
        zzatk.put(0, zzlo.zzv.zzb.TYPE_UNKNOWN);
        zzatk.put(1, zzlo.zzv.zzb.TYPE_CONTACT_INFO);
        zzatk.put(2, zzlo.zzv.zzb.TYPE_EMAIL);
        zzatk.put(3, zzlo.zzv.zzb.TYPE_ISBN);
        zzatk.put(4, zzlo.zzv.zzb.TYPE_PHONE);
        zzatk.put(5, zzlo.zzv.zzb.TYPE_PRODUCT);
        zzatk.put(6, zzlo.zzv.zzb.TYPE_SMS);
        zzatk.put(7, zzlo.zzv.zzb.TYPE_TEXT);
        zzatk.put(8, zzlo.zzv.zzb.TYPE_URL);
        zzatk.put(9, zzlo.zzv.zzb.TYPE_WIFI);
        zzatk.put(10, zzlo.zzv.zzb.TYPE_GEO);
        zzatk.put(11, zzlo.zzv.zzb.TYPE_CALENDAR_EVENT);
        zzatk.put(12, zzlo.zzv.zzb.TYPE_DRIVER_LICENSE);
    }

    public FirebaseVisionBarcode(@NonNull Barcode barcode) {
        this.zzatl = (Barcode) Preconditions.checkNotNull(barcode);
    }

    @Nullable
    public Rect getBoundingBox() {
        return this.zzatl.getBoundingBox();
    }

    @Nullable
    public CalendarEvent getCalendarEvent() {
        if (this.zzatl.calendarEvent != null) {
            return new CalendarEvent(this.zzatl.calendarEvent);
        }
        return null;
    }

    @Nullable
    public ContactInfo getContactInfo() {
        if (this.zzatl.contactInfo != null) {
            return new ContactInfo(this.zzatl.contactInfo);
        }
        return null;
    }

    @Nullable
    public Point[] getCornerPoints() {
        return this.zzatl.cornerPoints;
    }

    @Nullable
    public String getDisplayValue() {
        return this.zzatl.displayValue;
    }

    @Nullable
    public DriverLicense getDriverLicense() {
        if (this.zzatl.driverLicense != null) {
            return new DriverLicense(this.zzatl.driverLicense);
        }
        return null;
    }

    @Nullable
    public Email getEmail() {
        if (this.zzatl.email != null) {
            return new Email(this.zzatl.email);
        }
        return null;
    }

    @BarcodeFormat
    public int getFormat() {
        int i = this.zzatl.format;
        if (i > 4096 || i == 0) {
            return -1;
        }
        return i;
    }

    @Nullable
    public GeoPoint getGeoPoint() {
        if (this.zzatl.geoPoint != null) {
            return new GeoPoint(this.zzatl.geoPoint);
        }
        return null;
    }

    @Nullable
    public Phone getPhone() {
        if (this.zzatl.phone != null) {
            return new Phone(this.zzatl.phone);
        }
        return null;
    }

    @Nullable
    public String getRawValue() {
        return this.zzatl.rawValue;
    }

    @Nullable
    public Sms getSms() {
        if (this.zzatl.sms != null) {
            return new Sms(this.zzatl.sms);
        }
        return null;
    }

    @Nullable
    public UrlBookmark getUrl() {
        if (this.zzatl.url != null) {
            return new UrlBookmark(this.zzatl.url);
        }
        return null;
    }

    @BarcodeValueType
    public int getValueType() {
        return this.zzatl.valueFormat;
    }

    @Nullable
    public WiFi getWifi() {
        if (this.zzatl.wifi != null) {
            return new WiFi(this.zzatl.wifi);
        }
        return null;
    }

    public final zzlo.zzv.zza zzlr() {
        zzlo.zzv.zza zza = zzatj.get(Integer.valueOf(getFormat()));
        return zza == null ? zzlo.zzv.zza.FORMAT_UNKNOWN : zza;
    }

    public final zzlo.zzv.zzb zzls() {
        zzlo.zzv.zzb zzb = zzatk.get(Integer.valueOf(getValueType()));
        return zzb == null ? zzlo.zzv.zzb.TYPE_UNKNOWN : zzb;
    }
}
