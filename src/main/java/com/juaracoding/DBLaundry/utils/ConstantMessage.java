package com.juaracoding.DBLaundry.utils;

public  class ConstantMessage {

    /*
     * Global
     * */
    public final static String SUCCESS_SAVE = "DATA BERHASIL DIBUAT";
    public final static String WARNING_DATA_EMPTY = "DATA TIDAK ADA";
    public final static String SUCCESS_FIND = "DATA DITEMUKAN";




    /*
user
*/
    public final static String WARNING_NAMA_EMPTY = "NAMA TIDAK BOLEH KOSONG";

    public final static String WARNING_NAMA_BLANK = "NAMA TIDAK BOLEH BLANK";

    public final static String WARNING_NAMA_NULL = "NAMA TIDAK BOLEH NULL";

    public final static String WARNING_MAXSIMAL_NAMA = "NAMA MAKSIMAL KARAKTER 40";

    public final static String WARNING_EMAIL_NULL = "EMAIL TIDAK BOLEH NULL";

    public final static String WARNING_EMAIL_EMPTY = "EMAIL TIDAK BOLEH KOSONG";

    public final static String WARNING_EMAIL_BLANK = "EMAIL TIDAK BOLEH BLANK";

    public final static String WARNING_PASSWORD_NULL = "PASSWORD TIDAK BOLEH NULL";

    public final static String WARNING_PASSWORD_EMPTY = "PASSWORD TIDAK BOLEH KOSONG";

    public final static String WARNING_PASSWORD_BLANK = "PASSWORD TIDAK BOLEH BLANK";

    public final static String WARNING_PASSWORD_MINIMAL = "PASSWORD MINIMAL 8 KARAKTER";

    public final static String WARNING_NAMA_PAKET_EMPTY = "NAMA PAKET TIDAK BOELH KOSONG";

    public final static String WARNING_NAMA_PAKET_BLANK = "NAMA PAKET TIDAK BOLEH BLANK";

    public final static String WARNING_HARGA_PAKET_NULL = "HARGA PAKET TIDAK BOLEH NULL";

    public final static String WARNING_HARGA_PAKET_EMPTY = "HARGA PAKET TIDAK BOLEH EMPTY";

    public final static String WARNING_HARGA_PAKET_BLANK = "HARGA PAKET TIDAK BOLEH BLANK";

    public final static String WARNING_TIPE_PAKET_NULL = "HARGA PAKET TIDAK BOLEH NULL";

    public final static String WARNING_TIPE_PAKET_EMPTY = "HARGA PAKET TIDAK BOLEH EMPTY";

    public final static String WARNING_TIPE_PAKET_BLANK = "HARGA PAKET TIDAK BOLEH BLANK";

    public final static String WARNING_USERNAME_EMPTY = "USERNAME TIDAK BOLEH KOSONG";

    public final static String WARNING_USERNAME_NULL = "USERNAME TIDAK BOLEH NULL";

    public final static String WARNING_USERNAME_BLANK = "USERNAME TIDAK BOLEH BLANK";

    public final static String WARNING_USERNAME_MAX_LENGTH = "USERNAME MAKSIMAL 10 KARAKTER DAN MINIMAL 5 KARAKTER";

    public final static String ERROR_NOHP_IS_NULL = "NO HANDPHONE TIDAK BOLEH NULL!!";
    public final static String ERROR_NOHP_IS_EMPTY = "NO HANDPHONE TIDAK BOLEH KOSONG!!";

    public final static String REGEX_EMAIL_STANDARD_RFC5322  = "^[a-zA-Z0-9_!#$%&'*+/=?`{|}~^.-]+@[a-zA-Z0-9.-]+$";
    public final static String REGEX_DATE_YYYYMMDD  = "^([0-9][0-9])?[0-9][0-9]-(0[0-9]||1[0-2])-([0-2][0-9]||3[0-1])$";

    public final static String REGEX_DATE_DDMMYYYY  = "^([0-2][0-9]||3[0-1])-(0[0-9]||1[0-2])-([0-9][0-9])?[0-9][0-9]$";
    public final static String REGEX_PHONE = "^[\\+]?[(]?[0-9]{3}[)]?[-\\s\\.]?[0-9]{3}[-\\s\\.]?[0-9]{4,6}$";

    public final static String ERROR_PHONE_NUMBER_FORMAT_INVALID = "FORMAT NOMOR HANDPHONE TIDAK SESUAI (+628XX-xxx) ex : +62813-24";
    public final static String ERROR_EMAIL_FORMAT_INVALID = "FORMAT EMAIL TIDAK SESUAI (Nomor/Karakter@Nomor/Karakter Tanpa tanda | atau ')";

    public final static String ERROR_EMAIL_ISEXIST = "REGISTRASI GAGAL! EMAIL SUDAH TERDAFTAR";
    public final static String ERROR_NOHP_ISEXIST = "REGISTRASI GAGAL! NO HP SUDAH TERDAFTAR";
    public final static String ERROR_USERNAME_ISEXIST = "REGISTRASI GAGAL! USERNAME SUDAH TERDAFTAR";
    public final static String ERROR_USER_ISACTIVE = "REGISTRASI GAGAL! EMAIL SUDAH TERDAFTAR";
    public final static String ERROR_FLOW_INVALID = "PROSES TIDAK SESUAI DENGAN PROSEDUR";
    public final static String SUCCESS_CHECK_REGIS = "PROSES REGISTRASI AMAN";
}
