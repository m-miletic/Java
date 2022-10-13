package MyColor;
//import java.awt.Color;

//package MyColor;

public class ColorConverter {
    public static void main(String[] args) { // String[] args je niz tipa java.lang znaci u sebi ima java cmd line argumente

        var hexColor = "0x1FF0FF";

        MyColor c = MyColor.decode(hexColor); //string -> long | argument mu je string koji predstavlja neki broj

        float[] hsbCode = new float[3]; // deklaracija niza i alociranje memorije za njega

        MyColor.RGBtoHSB(c.getRed(), c.getGreen(), c.getBlue(), hsbCode);

        System.out.println("Boja u HEX formatu: 0x" +
                Integer.toHexString(c.getRGB() & 0x00FFFFFF));
        System.out.println("Boja u RGB formatu: " + c.getRed() + ", " +
                c.getGreen() + ", " + c.getBlue());
        System.out.println("Boja u HSB formatu: " + hsbCode[0] * 360 + "°, " +
                hsbCode[1] * 100 + "%, " + hsbCode[2] * 100 + "%");

        float [] cmykCode = new float[4];

        MyColor.RGBtoCMYK(c.getRed(), c.getGreen(), c.getBlue(), cmykCode);

        System.out.println("Boja u CMYK formatu: " + cmykCode[0] * 100 + "%, " +
                cmykCode[1] * 100 + "%, " + cmykCode[2] * 100 + "%, " + cmykCode[3] * 100 + "%");

        float [] hslCode = new float[3];

        MyColor.RGBtoHSL(c.getRed(), c.getGreen(), c.getBlue(), hslCode);

        System.out.println("Boja u HSL formatu: " + hslCode[0] * 60 + "°, " +
                hslCode[1] * 100 + "%, " + hslCode[2] * 100 + "%");
    }


}




