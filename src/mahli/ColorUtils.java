package mahli;

import java.util.ArrayList;
public class ColorUtils {
	 private ArrayList<ColorName> initColorList() {
	        ArrayList<ColorName> colorList = new ArrayList<ColorName>();
	        colorList.add(new ColorName("AliceBlue", 0xF0, 0xF8, 0xFF,"Sodalite"));
	        colorList.add(new ColorName("AntiqueWhite", 0xFA, 0xEB, 0xD7,"Kaolinite"));
	        colorList.add(new ColorName("Aqua", 0x00, 0xFF, 0xFF,"Smectite"));
	        colorList.add(new ColorName("Aquamarine", 0x7F, 0xFF, 0xD4,"Sodalite"));
	        colorList.add(new ColorName("Azure", 0xF0, 0xFF, 0xFF,"Illite"));
	        colorList.add(new ColorName("Beige", 0xF5, 0xF5, 0xDC,"Talc"));
	        colorList.add(new ColorName("Bisque", 0xFF, 0xE4, 0xC4,"Bismuth"));
	        colorList.add(new ColorName("Black", 0x00, 0x00, 0x00,"Copper"));
	        colorList.add(new ColorName("BlanchedAlmond", 0xFF, 0xEB, 0xCD,"Hornblende"));
	        colorList.add(new ColorName("Blue", 0x00, 0x00, 0xFF,"Sodalite"));
	        colorList.add(new ColorName("BlueViolet", 0x8A, 0x2B, 0xE2,"Pyroxene"));
	        colorList.add(new ColorName("Brown", 0xA5, 0x2A, 0x2A,"Mica"));
	        colorList.add(new ColorName("BurlyWood", 0xDE, 0xB8, 0x87,"Mica"));
	        colorList.add(new ColorName("CadetBlue", 0x5F, 0x9E, 0xA0,"Pyroxene"));
	        colorList.add(new ColorName("Chartreuse", 0x7F, 0xFF, 0x00,"Glaocophene"));
	        colorList.add(new ColorName("Chocolate", 0xD2, 0x69, 0x1E,"Mica"));
	        colorList.add(new ColorName("Coral", 0xFF, 0x7F, 0x50,"Choride"));
	        colorList.add(new ColorName("CornflowerBlue", 0x64, 0x95, 0xED,"Beryl"));
	        colorList.add(new ColorName("Cornsilk", 0xFF, 0xF8, 0xDC,"Epidote"));
	        colorList.add(new ColorName("Crimson", 0xDC, 0x14, 0x3C,"Andradite"));
	        colorList.add(new ColorName("Cyan", 0x00, 0xFF, 0xFF,"Almandine"));
	        colorList.add(new ColorName("DarkBlue", 0x00, 0x00, 0x8B,"Aquite"));
	        colorList.add(new ColorName("DarkCyan", 0x00, 0x8B, 0x8B,"Iron"));
	        colorList.add(new ColorName("DarkGoldenRod", 0xB8, 0x86, 0x0B,"Andradite"));
	        colorList.add(new ColorName("DarkGray", 0xA9, 0xA9, 0xA9,"Iron"));
	        colorList.add(new ColorName("DarkGreen", 0x00, 0x64, 0x00,"olivine"));
	        colorList.add(new ColorName("DarkKhaki", 0xBD, 0xB7, 0x6B,"chlorotoid"));
	        colorList.add(new ColorName("DarkMagenta", 0x8B, 0x00, 0x8B,"Zinc"));
	        colorList.add(new ColorName("DarkOrange", 0xFF, 0x8C, 0x00,"SPHALERITE"));
	        colorList.add(new ColorName("DarkOrchid", 0x99, 0x32, 0xCC,"BIOTITE"));
	        colorList.add(new ColorName("DarkRed", 0x8B, 0x00, 0x00,"GRAPHITE"));
	        colorList.add(new ColorName("DarkSalmon", 0xE9, 0x96, 0x7A,"PYRITE"));
	        colorList.add(new ColorName("DarkSeaGreen", 0x8F, 0xBC, 0x8F,"HEMATITE"));
	        colorList.add(new ColorName("DarkSlateBlue", 0x48, 0x3D, 0x8B,"MAGNETITE"));
	        colorList.add(new ColorName("DarkSlateGray", 0x2F, 0x4F, 0x4F,"PYRRHOTITE"));
	        colorList.add(new ColorName("DarkTurquoise", 0x00, 0xCE, 0xD1,"CHALCOPYRITE"));
	        colorList.add(new ColorName("DarkViolet", 0x94, 0x00, 0xD3,"BORNITE"));
	        colorList.add(new ColorName("DeepPink", 0xFF, 0x14, 0x93,"EPIDOTE"));
	        colorList.add(new ColorName("DeepSkyBlue", 0x00, 0xBF, 0xFF,"ORTHOCLASE"));
	        colorList.add(new ColorName("DimGray", 0x69, 0x69, 0x69,"PLAGIOCLASE"));
	        colorList.add(new ColorName("DodgerBlue", 0x1E, 0x90, 0xFF,"NEPHELINE"));
	        colorList.add(new ColorName("FireBrick", 0xB2, 0x22, 0x22,"AUGITE"));
	        colorList.add(new ColorName("FloralWhite", 0xFF, 0xFA, 0xF0,"HORNBLENDE"));
	        colorList.add(new ColorName("ForestGreen", 0x22, 0x8B, 0x22,"APATITE"));
	        colorList.add(new ColorName("Fuchsia", 0xFF, 0x00, 0xFF,"SERPENTINE"));
	        colorList.add(new ColorName("Gainsboro", 0xDC, 0xDC, 0xDC,"DOLOMITE"));
	        colorList.add(new ColorName("GhostWhite", 0xF8, 0xF8, 0xFF,"FLUORITE"));
	        colorList.add(new ColorName("Gold", 0xFF, 0xD7, 0x00,"BARITE"));
	        colorList.add(new ColorName("GoldenRod", 0xDA, 0xA5, 0x20,"CALCITE"));
	        colorList.add(new ColorName("Gray", 0x80, 0x80, 0x80,"Iron"));
	        colorList.add(new ColorName("Green", 0x00, 0x80, 0x00,"CHLORITE"));
	        colorList.add(new ColorName("GreenYellow", 0xAD, 0xFF, 0x2F,"MUSCOVITE"));
	        colorList.add(new ColorName("HoneyDew", 0xF0, 0xFF, 0xF0,"KAOLINITE"));
	        colorList.add(new ColorName("HotPink", 0xFF, 0x69, 0xB4,"HALITE"));
	        colorList.add(new ColorName("IndianRed", 0xCD, 0x5C, 0x5C,"GYPSUM"));
	        colorList.add(new ColorName("Indigo", 0x4B, 0x00, 0x82,"TALC"));
	        colorList.add(new ColorName("Ivory", 0xFF, 0xFF, 0xF0,"CORUNDUM"));
	        colorList.add(new ColorName("Khaki", 0xF0, 0xE6, 0x8C,"TOURMALINE"));
	        colorList.add(new ColorName("Lavender", 0xE6, 0xE6, 0xFA,"GARNET"));
	        colorList.add(new ColorName("LavenderBlush", 0xFF, 0xF0, 0xF5,"QUARTZ"));
	        colorList.add(new ColorName("LawnGreen", 0x7C, 0xFC, 0x00,"OLIVINE"));
	        colorList.add(new ColorName("LemonChiffon", 0xFF, 0xFA, 0xCD,"LIMONITE"));
	        colorList.add(new ColorName("LightBlue", 0xAD, 0xD8, 0xE6,"BAUXITE"));
	        colorList.add(new ColorName("LightCoral", 0xF0, 0x80, 0x80,"GOETHITE"));
	        colorList.add(new ColorName("LightCyan", 0xE0, 0xFF, 0xFF,"SPHALERITE"));
	        colorList.add(new ColorName("LightGoldenRodYellow", 0xFA, 0xFA, 0xD2,"BIOTITE"));
	        colorList.add(new ColorName("LightGray", 0xD3, 0xD3, 0xD3,"GRAPHITE"));
	        colorList.add(new ColorName("LightGreen", 0x90, 0xEE, 0x90,"PYRITE"));
	        colorList.add(new ColorName("LightPink", 0xFF, 0xB6, 0xC1,"HEMATITE"));
	        colorList.add(new ColorName("LightSalmon", 0xFF, 0xA0, 0x7A,"MAGNETITE"));
	        colorList.add(new ColorName("LightSeaGreen", 0x20, 0xB2, 0xAA,"PYRRHOTITE"));
	        colorList.add(new ColorName("LightSkyBlue", 0x87, 0xCE, 0xFA,"CHALCOPYRITE"));
	        colorList.add(new ColorName("LightSlateGray", 0x77, 0x88, 0x99,"BORNITE"));
	        colorList.add(new ColorName("LightSteelBlue", 0xB0, 0xC4, 0xDE,"EPIDOTE"));
	        colorList.add(new ColorName("LightYellow", 0xFF, 0xFF, 0xE0,"ORTHOCLASE"));
	        colorList.add(new ColorName("Lime", 0x00, 0xFF, 0x00,"PLAGIOCLASE"));
	        colorList.add(new ColorName("LimeGreen", 0x32, 0xCD, 0x32,"NEPHELINE"));
	        colorList.add(new ColorName("Linen", 0xFA, 0xF0, 0xE6,"AUGITE"));
	        colorList.add(new ColorName("Magenta", 0xFF, 0x00, 0xFF,"HORNBLENDE"));
	        colorList.add(new ColorName("Maroon", 0x80, 0x00, 0x00,"APATITE"));
	        colorList.add(new ColorName("MediumAquaMarine", 0x66, 0xCD, 0xAA,"SERPENTINE"));
	        colorList.add(new ColorName("MediumBlue", 0x00, 0x00, 0xCD,"DOLOMITE"));
	        colorList.add(new ColorName("MediumOrchid", 0xBA, 0x55, 0xD3,"FLUORITE"));
	        colorList.add(new ColorName("MediumPurple", 0x93, 0x70, 0xDB,"BARITE"));
	        colorList.add(new ColorName("MediumSeaGreen", 0x3C, 0xB3, 0x71,"CALCITE"));
	        colorList.add(new ColorName("MediumSlateBlue", 0x7B, 0x68, 0xEE,"PHLOGOPITE"));
	        colorList.add(new ColorName("MediumSpringGreen", 0x00, 0xFA, 0x9A,"CHLORITE"));
	        colorList.add(new ColorName("MediumTurquoise", 0x48, 0xD1, 0xCC,"MUSCOVITE"));
	        colorList.add(new ColorName("MediumVioletRed", 0xC7, 0x15, 0x85,"KAOLINITE"));
	        colorList.add(new ColorName("MidnightBlue", 0x19, 0x19, 0x70,"HALITE"));
	        colorList.add(new ColorName("MintCream", 0xF5, 0xFF, 0xFA,"GYPSUM"));
	        colorList.add(new ColorName("MistyRose", 0xFF, 0xE4, 0xE1,"TALC"));
	        colorList.add(new ColorName("Moccasin", 0xFF, 0xE4, 0xB5,"CORUNDUM"));
	        colorList.add(new ColorName("NavajoWhite", 0xFF, 0xDE, 0xAD,"TOURMALINE"));
	        colorList.add(new ColorName("Navy", 0x00, 0x00, 0x80,"GARNET"));
	        colorList.add(new ColorName("OldLace", 0xFD, 0xF5, 0xE6,"QUARTZ"));
	        colorList.add(new ColorName("Olive", 0x80, 0x80, 0x00,"OLIVINE"));
	        colorList.add(new ColorName("OliveDrab", 0x6B, 0x8E, 0x23,"LIMONITE"));
	        colorList.add(new ColorName("Orange", 0xFF, 0xA5, 0x00,"BAUXITE"));
	        colorList.add(new ColorName("OrangeRed", 0xFF, 0x45, 0x00,"GOETHITE"));
	        colorList.add(new ColorName("Orchid", 0xDA, 0x70, 0xD6,"SPHALERITE"));
	        colorList.add(new ColorName("PaleGoldenRod", 0xEE, 0xE8, 0xAA,"BIOTITE"));
	        colorList.add(new ColorName("PaleGreen", 0x98, 0xFB, 0x98,"GRAPHITE"));
	        colorList.add(new ColorName("PaleTurquoise", 0xAF, 0xEE, 0xEE,"PYRITE"));
	        colorList.add(new ColorName("PaleVioletRed", 0xDB, 0x70, 0x93,"HEMATITE"));
	        colorList.add(new ColorName("PapayaWhip", 0xFF, 0xEF, 0xD5,"MAGNETITE"));
	        colorList.add(new ColorName("PeachPuff", 0xFF, 0xDA, 0xB9,"PYRRHOTITE"));
	        colorList.add(new ColorName("Peru", 0xCD, 0x85, 0x3F,"CHALCOPYRITE"));
	        colorList.add(new ColorName("Pink", 0xFF, 0xC0, 0xCB,"BORNITE"));
	        colorList.add(new ColorName("Plum", 0xDD, 0xA0, 0xDD,"EPIDOTE"));
	        colorList.add(new ColorName("PowderBlue", 0xB0, 0xE0, 0xE6,"ORTHOCLASE"));
	        colorList.add(new ColorName("Purple", 0x80, 0x00, 0x80,"PLAGIOCLASE"));
	        colorList.add(new ColorName("Red", 0xFF, 0x00, 0x00,"NEPHELINE"));
	        colorList.add(new ColorName("RosyBrown", 0xBC, 0x8F, 0x8F,"AUGITE"));
	        colorList.add(new ColorName("RoyalBlue", 0x41, 0x69, 0xE1,"HORNBLENDE"));
	        colorList.add(new ColorName("SaddleBrown", 0x8B, 0x45, 0x13,"APATITE"));
	        colorList.add(new ColorName("Salmon", 0xFA, 0x80, 0x72,"SERPENTINE"));
	        colorList.add(new ColorName("SandyBrown", 0xF4, 0xA4, 0x60,"DOLOMITE"));
	        colorList.add(new ColorName("SeaGreen", 0x2E, 0x8B, 0x57,"FLUORITE"));
	        colorList.add(new ColorName("SeaShell", 0xFF, 0xF5, 0xEE,"BARITE"));
	        colorList.add(new ColorName("Sienna", 0xA0, 0x52, 0x2D,"CALCITE"));
	        colorList.add(new ColorName("Silver", 0xC0, 0xC0, 0xC0,"PHLOGOPITE"));
	        colorList.add(new ColorName("SkyBlue", 0x87, 0xCE, 0xEB,"CHLORITE"));
	        colorList.add(new ColorName("SlateBlue", 0x6A, 0x5A, 0xCD,"MUSCOVITE"));
	        colorList.add(new ColorName("SlateGray", 0x70, 0x80, 0x90,"KAOLINITE"));
	        colorList.add(new ColorName("Snow", 0xFF, 0xFA, 0xFA,"HALITE"));
	        colorList.add(new ColorName("SpringGreen", 0x00, 0xFF, 0x7F,"GYPSUM"));
	        colorList.add(new ColorName("SteelBlue", 0x46, 0x82, 0xB4,"TALC"));
	        colorList.add(new ColorName("Tan", 0xD2, 0xB4, 0x8C,"CORUNDUM"));
	        colorList.add(new ColorName("Teal", 0x00, 0x80, 0x80,"TOURMALINE"));
	        colorList.add(new ColorName("Thistle", 0xD8, 0xBF, 0xD8,"GARNET"));
	        colorList.add(new ColorName("Tomato", 0xFF, 0x63, 0x47,"GOETHITE"));
	        colorList.add(new ColorName("Turquoise", 0x40, 0xE0, 0xD0,"SPHALERITE"));
	        colorList.add(new ColorName("Violet", 0xEE, 0x82, 0xEE,"BIOTITE"));
	        colorList.add(new ColorName("Wheat", 0xF5, 0xDE, 0xB3,"GRAPHITE"));
	        colorList.add(new ColorName("White", 0xFF, 0xFF, 0xFF,"PYRITE"));
	        colorList.add(new ColorName("WhiteSmoke", 0xF5, 0xF5, 0xF5,"HEMATITE"));
	        colorList.add(new ColorName("Yellow", 0xFF, 0xFF, 0x00,"MAGNETITE"));
	        colorList.add(new ColorName("YellowGreen", 0x9A, 0xCD, 0x32,"PYRRHOTITE"));
       return colorList;
	    }
	 
	 
	 public String getColorNameFromRgb(int r, int g, int b) {
	        ArrayList<ColorName> colorList = initColorList();
	        ColorName closestMatch = null;
	        int minMSE = Integer.MAX_VALUE;
	        int mse;
	        for (ColorName c : colorList) {
	            mse = c.computeMSE(r, g, b);
	            if (mse < minMSE) {
	                minMSE = mse;
	                closestMatch = c;
	            }
	        }

	        if (closestMatch != null) {
	            return closestMatch.getName()+":"+closestMatch.getMineral();
	        } else {
	            return "No matched color name.";
	        }
	    }

}
