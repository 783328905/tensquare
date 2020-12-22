package com.tensquare.encrypt.rsa;

/**
 * rsa加解密用的公钥和私钥
 * @author Administrator
 *
 */
public class RsaKeys {

	//生成秘钥对的方法可以参考这篇帖子
	//https://www.cnblogs.com/yucy/p/8962823.html

//	//服务器公钥
//	private static final String serverPubKey = "MIIBIjANBgkqhkiG9w0BAQEFAAOCAQ8AMIIBCgKCAQEA6Dw9nwjBmDD/Ca1QnRGy"
//											 + "GjtLbF4CX2EGGS7iqwPToV2UUtTDDemq69P8E+WJ4n5W7Iln3pgK+32y19B4oT5q"
//											 + "iUwXbbEaAXPPZFmT5svPH6XxiQgsiaeZtwQjY61qDga6UH2mYGp0GbrP3i9TjPNt"
//											 + "IeSwUSaH2YZfwNgFWqj+y/0jjl8DUsN2tIFVSNpNTZNQ/VX4Dln0Z5DBPK1mSskd"
//											 + "N6uPUj9Ga/IKnwUIv+wL1VWjLNlUjcEHsUE+YE2FN03VnWDJ/VHs7UdHha4d/nUH"
//											 + "rZrJsKkauqnwJsYbijQU+a0HubwXB7BYMlKovikwNpdMS3+lBzjS5KIu6mRv1GoE"
//											 + "vQIDAQAB";
//
//	//服务器私钥(经过pkcs8格式处理)
//	private static final String serverPrvKeyPkcs8 = "MIIEvgIBADANBgkqhkiG9w0BAQEFAASCBKgwggSkAgEAAoIBAQDoPD2fCMGYMP8J"
//				 								 + "rVCdEbIaO0tsXgJfYQYZLuKrA9OhXZRS1MMN6arr0/wT5YniflbsiWfemAr7fbLX"
//				 								 + "0HihPmqJTBdtsRoBc89kWZPmy88fpfGJCCyJp5m3BCNjrWoOBrpQfaZganQZus/e"
//				 								 + "L1OM820h5LBRJofZhl/A2AVaqP7L/SOOXwNSw3a0gVVI2k1Nk1D9VfgOWfRnkME8"
//				 								 + "rWZKyR03q49SP0Zr8gqfBQi/7AvVVaMs2VSNwQexQT5gTYU3TdWdYMn9UeztR0eF"
//				 								 + "rh3+dQetmsmwqRq6qfAmxhuKNBT5rQe5vBcHsFgyUqi+KTA2l0xLf6UHONLkoi7q"
//				 								 + "ZG/UagS9AgMBAAECggEBANP72QvIBF8Vqld8+q7FLlu/cDN1BJlniReHsqQEFDOh"
//				 								 + "pfiN+ZZDix9FGz5WMiyqwlGbg1KuWqgBrzRMOTCGNt0oteIM3P4iZlblZZoww9nR"
//				 								 + "sc4xxeXJNQjYIC2mZ75x6bP7Xdl4ko3B9miLrqpksWNUypTopOysOc9f4FNHG326"
//				 								 + "0EMazVaXRCAIapTlcUpcwuRB1HT4N6iKL5Mzk3bzafLxfxbGCgTYiRQNeRyhXOnD"
//				 								 + "eJox64b5QkFjKn2G66B5RFZIQ+V+rOGsQElAMbW95jl0VoxUs6p5aNEe6jTgRzAT"
//				 								 + "kqM2v8As0GWi6yogQlsnR0WBn1ztggXTghQs2iDZ0YkCgYEA/LzC5Q8T15K2bM/N"
//				 								 + "K3ghIDBclB++Lw/xK1eONTXN+pBBqVQETtF3wxy6PiLV6PpJT/JIP27Q9VbtM9UF"
//				 								 + "3lepW6Z03VLqEVZo0fdVVyp8oHqv3I8Vo4JFPBDVxFiezygca/drtGMoce0wLWqu"
//				 								 + "bXlUmQlj+PTbXJMz4VTXuPl1cesCgYEA6zu5k1DsfPolcr3y7K9XpzkwBrT/L7LE"
//				 								 + "EiUGYIvgAkiIta2NDO/BIPdsq6OfkMdycAwkWFiGrJ7/VgU+hffIZwjZesr4HQuC"
//				 								 + "0APsqtUrk2yx+f33ZbrS39gcm/STDkVepeo1dsk2DMp7iCaxttYtMuqz3BNEwfRS"
//				 								 + "kIyKujP5kfcCgYEA1N2vUPm3/pNFLrR+26PcUp4o+2EY785/k7+0uMBOckFZ7GIl"
//				 								 + "FrV6J01k17zDaeyUHs+zZinRuTGzqzo6LSCsNdMnDtos5tleg6nLqRTRzuBGin/A"
//				 								 + "++xWn9aWFT+G0ne4KH9FqbLyd7IMJ9R4gR/1zseH+kFRGNGqmpi48MS61G0CgYBc"
//				 								 + "PBniwotH4cmHOSWkWohTAGBtcNDSghTRTIU4m//kxU4ddoRk+ylN5NZOYqTxXtLn"
//				 								 + "Tkt9/JAp5VoW/41peCOzCsxDkoxAzz+mkrNctKMWdjs+268Cy4Nd09475GU45khb"
//				 								 + "Y/88qV6xGz/evdVW7JniahbGByQhrMwm84R9yF1mNwKBgCIJZOFp9xV2997IY83S"
//				 								 + "habB/YSFbfZyojV+VFBRl4uc6OCpXdtSYzmsaRcMjN6Ikn7Mb9zgRHR8mPmtbVfj"
//				 								 + "B8W6V1H2KOPfn/LAM7Z0qw0MW4jimBhfhn4HY30AQ6GeImb2OqOuh3RBbeuuD+7m"
//				 								 + "LpFZC9zGggf9RK3PfqKeq30q";

	//服务器公钥
	private static final String serverPubKey = "MIIBIjANBgkqhkiG9w0BAQEFAAOCAQ8AMIIBCgKCAQEApp2uVB/jM1Kt8siF4nu9\n" +
			"yh6oft/sTXeoFfKm40OhbuxZWsbtcdZ4Iypuz8EczTikPMx//S2bXaMSQZv5S8pW\n" +
			"Sahhhu6YxNjPlqfS3Fb3MJ2GHuzkEhkMarzRnStRcnpkpPnYLarmFzLh0ds+L6Ta\n" +
			"1cIcOnmYDvXUb90K1074D/ph4ySv5VP/B3b92GhvoGLfhYAFDTs0xipDkXChK/uG\n" +
			"Jzg1sjBO6irlLTPiPf3m3WG9nSpqKtv2CNfHiC7daCNje1KKOEcBVxEvysgRVQ5d\n" +
			"pfjVgF2MIB0QCo71dJ6dnvO45DKgFGuyKM8ACQQ1V3wikpkXzDLkaCRB2TBZHMkK\n" +
			"GQIDAQAB";

	//服务器私钥(经过pkcs8格式处理)
	private static final String serverPrvKeyPkcs8 = "MIIEvQIBADANBgkqhkiG9w0BAQEFAASCBKcwggSjAgEAAoIBAQCmna5UH+MzUq3y\n" +
			"yIXie73KHqh+3+xNd6gV8qbjQ6Fu7Flaxu1x1ngjKm7PwRzNOKQ8zH/9LZtdoxJB\n" +
			"m/lLylZJqGGG7pjE2M+Wp9LcVvcwnYYe7OQSGQxqvNGdK1FyemSk+dgtquYXMuHR\n" +
			"2z4vpNrVwhw6eZgO9dRv3QrXTvgP+mHjJK/lU/8Hdv3YaG+gYt+FgAUNOzTGKkOR\n" +
			"cKEr+4YnODWyME7qKuUtM+I9/ebdYb2dKmoq2/YI18eILt1oI2N7Uoo4RwFXES/K\n" +
			"yBFVDl2l+NWAXYwgHRAKjvV0np2e87jkMqAUa7IozwAJBDVXfCKSmRfMMuRoJEHZ\n" +
			"MFkcyQoZAgMBAAECggEAE2XcO/MScpE3q0JpQ/sAF54GI+9SBZreuN9tSpw/GTjv\n" +
			"FkB+uHgtuHpiOFHVVpc3l9R/OiyrH/WyVG0iUw6ViTHPWPRCWPidAn87b2e4k7Jj\n" +
			"QQp3axv7dBnTlabLbraNLR/haT+yVhUYcWRDEyzwRV66BpzNdUBtoTBs6LBK9SET\n" +
			"SedxsZFPP1W6Ao6o025ZPAqTeVm/rJAGhN2OCun6/ngBlNJBrOGkvax9tmnghOYQ\n" +
			"yY4xDl+8rkjktFPaBTxis/iXK+B35DAloii73ANL6uO4hxH+jUi5PwK57SflCsYy\n" +
			"iwdBE65FltPCvhz/Y5DUq/sCIzjwGomCVhpXOGTXYQKBgQDZKrHdk74XQcCSvgv4\n" +
			"hzXKFMo3MoJZK9Ap+tiVjQSV9GymznIZg4eCor7y6An6wB31zThWvwYHLSRX3Saj\n" +
			"6uNpxmdElfNNzzBiM2pYKy9cWaYuc70nCMRW+tQSWjAyn8uqINoi77Z3OxbkyatY\n" +
			"C1GpAC1OD9MmLjeVuZuShgvKLQKBgQDEaOf/cgevhJbm1RZ/Nv9uVuG0wpdFjIno\n" +
			"kqHhD8PO95OGaCTLEhxRc80yV4KNm0P+C28Ai9FEe0mUqxSghhgNBUQC/A51TcPj\n" +
			"MDc0irrWrl+OVCQy7AWhhFteCnd4ZRy9daWFKhka0PQchtI0T9ln5YGGjrUfEwR2\n" +
			"/Yl1PBSPHQKBgHlF5GnudXsbVuUbiCfTqfAdyAcnB6+wTeEDmit5wpPTGVHVe9ZP\n" +
			"oz8Kj0Oq3NfQrWp0nf+jUUR0qxT+BPQI2dJZ35Vnf6V2DHjqaXqvMxWJYsoSDBFe\n" +
			"TfacwA03nC7JGbz595cG8oiG0PQWwr66I3ITDTnIkIuDx7qvGferL65JAoGBAL/j\n" +
			"8o3KvaGIu9nIZFNjy0jlD10IJLzpYq9b21ZEjby+e158jz3rcslNhzPg9YceI96n\n" +
			"EwsIjysF/MyIHP38JLFL7LexxPpsQK7NI5Js27/02xz2LeumVpYH9zA4r6JrvxmH\n" +
			"QHCKwb5GWckpG2PKg5CTVOm+sUwry1fpuwtT6I4tAoGAesT2dCdyDFMfq7ZfgodG\n" +
			"MwtMWvHhXd1NRkCV7SOClRJBERLyHc7RFThidb5KQW5M+5Yuyt+XKLyXsEHb/NP0\n" +
			"gP6rCGwOLbCJ2iQYU6jrr0NhvAmY3g5hs4vPYkgn7vW4eHoTXrGhxCGq3prd3yi6\n" +
			"Ry8kbyrd0yy3UZjky9tbiWY=";

	public static String getServerPubKey() {
		return serverPubKey;
	}

	public static String getServerPrvKeyPkcs8() {
		return serverPrvKeyPkcs8;
	}
	
}
