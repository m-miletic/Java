package MyColor;

public class MyColor {
    int r;
    int g;
    int b;
    public MyColor(int i, int i1, int i2) {
        r = i;
        g = i1;
        b = i2;
    }
    public int getRGB() {
        int value = ((255 & 0xFF) << 24) |
                ((r & 0xFF) << 16) |
                ((g & 0xFF) << 8)  |
                ((b & 0xFF) << 0);
        return value;
    }
    public int getRed() {
        return (getRGB() >> 16) & 0xFF;
    }
    public int getGreen() {
        return (getRGB() >> 8) & 0xFF;
    }
    public int getBlue() {
        return (getRGB() >> 0) & 0xFF;
    }

//    decode() is a built in function in java that decodes a String into a Long.
//    It accepts decimal, hexadecimal, and octal numbers.
//        Syntax: public static Long decode(String number) throws NumberFormatException Parameter:
//    number- the number that has to be decoded into a Long
    public static MyColor decode(String nm) throws NumberFormatException {
        Integer intval = Integer.decode(nm);
        int i = intval.intValue();
        return new MyColor((i >> 16) & 0xFF, (i >> 8) & 0xFF, i & 0xFF);
    }
    // RGBtoHSB
    public static float[] RGBtoHSB(int r, int g, int b, float[] hsbvals) {
        float hue, saturation, brightness;
        if (hsbvals == null) {
            hsbvals = new float[3];
        }
        int cmax = (r > g) ? r : g;
        if (b > cmax) cmax = b;
        int cmin = (r < g) ? r : g;
        if (b < cmin) cmin = b;

        brightness = ((float) cmax) / 255.0f;
        if (cmax != 0)
            saturation = ((float) (cmax - cmin)) / ((float) cmax);
        else
            saturation = 0;
        if (saturation == 0)
            hue = 0;
        else {
            float redc = ((float) (cmax - r)) / ((float) (cmax - cmin));
            float greenc = ((float) (cmax - g)) / ((float) (cmax - cmin));
            float bluec = ((float) (cmax - b)) / ((float) (cmax - cmin));
            if (r == cmax)
                hue = bluec - greenc;
            else if (g == cmax)
                hue = 2.0f + redc - bluec;
            else
                hue = 4.0f + greenc - redc;
            hue = hue / 6.0f;
            if (hue < 0)
                hue = hue + 1.0f;
        }
        hsbvals[0] = hue;
        hsbvals[1] = saturation;
        hsbvals[2] = brightness;
        return hsbvals;
    }
    // RGBtoCMYK
    public static float[] RGBtoCMYK(float r, float g, float b, float[] cmykvals) {
        var Cyan = 0f;
        var Magenta = 0f;
        var Yellow = 0f;
        var Black = 0f;

        Cyan = (1 - ( r / 255 ));
        Magenta = (1 - ( g / 255));
        Yellow = (1 - ( b / 255 ));

        var _black = 1f;

        if ( Cyan < _black ) {
            _black = Cyan;
        }
        if ( Magenta < _black ){
            _black = Magenta;
        }
        if ( Yellow < _black ) {
            _black = Yellow;
        }

        if ( _black == 1 ) { //Black
            Cyan = 0;
            Magenta = 0;
            Yellow = 0;
        }
        else {
            Cyan = ( Cyan - _black ) / ( 1 - _black );
            Magenta = ( Magenta - _black ) / ( 1 - _black );
            Yellow = ( Yellow - _black ) / ( 1 - _black );
        }
        Black = _black;

        cmykvals[0] = Cyan;
        cmykvals[1] = Magenta;
        cmykvals[2] = Yellow;
        cmykvals[3] = Black;

        return cmykvals;
    }

    // RGBtoHSL

    public static float [] RGBtoHSL(float r, float g, float b, float[] hslvals){

        var Hue = 0f;
        var Saturation = 0f;
        var Lightness = 0f;

        var max = 0f;
        var min = 0f;
        var diff = 0f;

        max = Math.max(Math.max(r, g), b);
        min = Math.min(Math.min(r, g), b);
        diff = (max - min) / 255;

        Lightness = (max + min) / 510;

        if(Lightness > 0) {
            Saturation = diff / (1 - (Math.abs(2*Lightness-1)));
        }
        if (Lightness == 0){
            Saturation = 0;
        }

        if(b == max){
            Hue = 4 + (r-g)/(max-min);
        }
        if(g == max){
            Hue = 2 + (b-r)/(max-min);
        }
        if(r == max){
            Hue = ((g-b)/(max-min));
        }

        hslvals[0] = Hue;
        hslvals[1] = Saturation;
        hslvals[2] = Lightness;

        return hslvals;
    }
}
