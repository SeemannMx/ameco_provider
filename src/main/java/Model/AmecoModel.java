package Model;

/**
 * Model of Ameco Table 1
 */
public class AmecoModel {

    private final String TAG = "AMECO_MODEL ";

    String country;
    String subChapter;
    String title;
    String unit;
    String year2018;

    /**
     * create Table Model Ameco 1
     *
     * @param c     - country
     * @param sc    - subchapter
     * @param t     - title
     * @param u     - unit
     * @param y     - year 2018
     */
    public AmecoModel(String c, String sc, String t, String u, String y) {
        country = c;
        subChapter = sc;
        title = t;
        unit = u;
        year2018 = y;
    }
}
