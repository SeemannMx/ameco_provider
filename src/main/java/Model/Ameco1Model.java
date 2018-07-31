package Model;

public class Ameco1Model {

    private final String TAG = "AMECO_1 ";

    String country;
    String subChapter;
    String title;
    String unit;
    String year2018;

    /**
     * create Table Model Ameco 1
     * @param c     - country
     * @param sc    - subchapter
     * @param t     - title
     * @param u     - unit
     * @param y     - year 2018
     */
    public Ameco1Model(String c, String sc, String t, String u, String y) {
        country = c;
        subChapter = sc;
        title = t;
        unit = u;
        year2018 = y;
    }
}
