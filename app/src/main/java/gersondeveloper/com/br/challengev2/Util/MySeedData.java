package gersondeveloper.com.br.challengev2.Util;

import java.math.BigDecimal;

import gersondeveloper.com.br.challengev2.R;

/**
 * Created by gersoncardoso on 04/10/2016.
 */

public class MySeedData {

    public static String[] typeArray = {"Console", "Console","Devices"};

    public static String[] nameArray = {"PS4", "Xbox One","Xbox Elite"};

    public static BigDecimal[] productValueArray = {new BigDecimal("1400.00"), new BigDecimal("1200.00"), new BigDecimal("900.00")};

    public static String[] productDescriptionArray = {"Descrição PS4","Descrição Xbox One","Joystick Descriptiojn"};

    public static Integer[] drawableArray = {
            R.drawable.playstation_4_20th_anniversary, R.drawable.xbox_one, R.drawable.joystick
    };

}
