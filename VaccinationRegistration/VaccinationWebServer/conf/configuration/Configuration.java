package configuration;

import java.awt.Color;
import java.awt.Font;
import java.awt.GradientPaint;
import java.awt.Graphics2D;
import java.awt.RenderingHints;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.util.HashMap;
import java.util.Random;
import javax.imageio.ImageIO;
import org.json.simple.JSONArray;
import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;
import service.utils.Utils;

public class Configuration {

    public static String STATIC_URL;
    public static String captcha_url;
    public static int index = 1;
    public static HashMap<String, String> mapSession = new HashMap<String, String>();
    public static HashMap<String, Long> mapTimeSession = new HashMap<String, Long>();
    public static final String cookieName = "keylog";
    public static String path_static_user;
    public static int evenId;
    public static int isDebug;
    public static int enable_captcha;
    public static HashMap<Integer, JSONObject> mapCaptcha = new HashMap<Integer, JSONObject>();

    public static void init() {
        evenId = Config.getParamInt("rest", "eventID");
        isDebug = Config.getParamInt("rest", "isdebug");
        STATIC_URL = Config.getParam("url", "static_url");
        captcha_url = Config.getParam("url", "captcha_url");
        path_static_user = Config.getParamString("url", "log_static", "./staticdata/user.json");
        loadUser();
        enable_captcha = Config.getParamInt("rest", "gen_captcha");
        if (enable_captcha == 1) {
            for (int i = 0; i < 150; i++) {
                Configuration.mapCaptcha.put(i, Configuration.generatorCapcha());
            }
        }
    }

    public static JSONObject generatorCapcha() {
        JSONObject mapjson = new JSONObject();
        try {
            int width = 120;
            int height = 50;
            char data[][] = {
                {'B', '7', '3', '7'}, {'5', '4', 'C', '5'}, {'3', 'A', 'C', '0'}, {'A', '4', '8', '8'}, {'A', '2', '0', '5'}, {'C', '0', '2', '6'}, {'5', '9', '9', '7'}, {'7', 'B', '4', '9'}, {'A', '5', '4', 'C'}, {'2', '6', 'B', '7'}, {'0', '9', 'A', 'B'}, {'C', '9', '5', '5'}, {'A', '8', '2', '8'}, {'3', '8', '8', '3'}, {'9', 'C', '1', '9'}, {'8', '2', '9', '6'}, {'3', '3', 'B', '7'}, {'3', '5', '7', '5'}, {'0', 'B', 'B', 'C'}, {'5', '1', '7', 'B'}, {'5', '5', '4', '0'}, {'8', '8', '3', '3'}, {'9', '4', '7', '5'}, {'6', '6', '3', '2'}, {'B', 'C', '5', '0'}, {'6', '7', '2', '9'}, {'3', '1', '4', '5'}, {'4', '6', '5', '6'}, {'0', '0', '2', '9'}, {'A', '1', '5', '4'}, {'A', '5', '9', '1'}, {'5', '8', '8', '9'}, {'A', '8', '6', '2'}, {'4', '5', '9', '0'}, {'A', 'A', '2', '9'}, {'8', '2', 'C', '3'}, {'6', '0', '5', '6'}, {'6', 'C', '2', 'C'}, {'A', '4', 'B', 'A'}, {'8', '6', '6', '4'}, {'9', 'C', 'C', '9'}, {'3', '4', '3', '3'}, {'9', 'A', '9', '3'}, {'9', '4', 'B', '1'}, {'7', 'A', '5', 'A'}, {'C', '1', 'B', '5'}, {'6', 'B', '5', '9'}, {'4', '4', '2', '5'}, {'3', '0', 'B', 'A'}, {'3', 'C', '3', 'C'}, {'5', '1', '2', 'C'}, {'2', '2', '3', 'A'}, {'8', 'A', '6', '3'}, {'A', '1', 'C', '6'}, {'6', '4', '3', '2'}, {'C', '9', '5', '3'}, {'8', '5', '1', '3'}, {'4', 'A', '7', '4'}, {'4', '0', '4', '3'}, {'0', '4', 'B', '1'}, {'2', '7', '0', '5'}, {'6', '9', '3', 'A'}, {'A', '4', '3', '6'}, {'6', '4', '1', '6'}, {'0', '0', '1', 'C'}, {'5', '4', '0', '0'}, {'6', '2', '3', 'B'}, {'5', 'A', 'C', '7'}, {'5', '5', 'A', '7'}, {'9', '0', 'B', '8'}, {'0', '1', '4', '5'}, {'4', 'A', '0', '3'}, {'7', '6', '4', 'B'}, {'5', '3', '6', '7'}, {'C', '0', 'C', '0'}, {'6', '0', 'A', 'B'}, {'4', '0', '5', 'C'}, {'4', 'B', '1', '0'}, {'2', 'B', '6', 'B'}, {'1', 'A', '3', '6'}, {'7', '4', '8', '5'}, {'C', 'B', 'A', '6'}, {'2', 'C', '1', '8'}, {'B', 'B', '2', '3'}, {'B', '2', '3', '7'}, {'6', 'A', '6', 'C'}, {'9', '2', '0', 'B'}, {'A', '7', 'B', '7'}, {'9', 'B', '7', 'A'}, {'4', '5', '6', '9'}, {'6', '5', '4', 'B'}, {'C', '2', '9', '7'}, {'0', '8', '6', '8'}, {'8', '1', 'A', '4'}, {'A', '9', '5', '7'}, {'5', '8', 'B', '0'}, {'4', '3', '3', '9'}, {'4', '7', '1', 'A'}, {'0', 'C', '2', 'B'}, {'2', '0', '2', 'B'}, {'1', '3', '7', '2'}, {'5', '6', '8', 'C'}, {'1', '9', '3', '7'}, {'7', '8', '8', '9'}, {'5', '5', '7', '8'}, {'6', '1', 'C', '4'}, {'1', '9', '5', 'B'}, {'9', '2', '7', '1'}, {'B', '9', '6', '5'}, {'7', '3', '7', 'C'}, {'7', '3', '1', '2'}, {'6', '4', 'A', 'A'}, {'4', '5', 'C', '6'}, {'8', '4', '6', '5'}, {'0', '9', 'A', '5'}, {'9', 'B', '6', 'C'}, {'B', '2', '6', '4'}, {'1', 'A', 'A', '7'}, {'9', '7', 'B', '1'}, {'7', '3', 'A', 'A'}, {'B', '5', '0', '3'}, {'5', '2', '2', '8'}, {'9', 'C', '1', '3'}, {'4', '2', '0', '8'}, {'C', 'A', 'B', '7'}, {'C', 'C', 'A', 'C'}, {'A', '0', '7', '5'}, {'1', '4', '4', 'C'}, {'A', '1', '8', '5'}, {'9', '3', '6', '7'}, {'5', '6', '4', '2'}, {'A', 'A', '8', '4'}, {'C', '4', '2', '6'}, {'7', '2', '9', 'B'}, {'8', '2', '1', 'B'}, {'A', '1', '0', '8'}, {'8', 'C', '7', '2'}, {'B', '8', '8', '4'}, {'C', '5', 'A', '3'}, {'2', '2', '4', '2'}, {'0', '7', '3', '1'}, {'3', 'A', '3', '7'}, {'2', 'B', 'C', '4'}, {'5', '1', '4', '1'}, {'7', '3', '9', '0'}, {'5', '7', '0', '4'}, {'C', '1', '6', '5'}, {'8', '3', '1', '9'}, {'7', 'A', 'C', 'C'}, {'6', '2', '1', '9'}

            };
            BufferedImage bufferedImage = new BufferedImage(width, height,
                    BufferedImage.TYPE_INT_RGB);
            Graphics2D g2d = bufferedImage.createGraphics();
            Font font = new Font("Georgia", Font.BOLD, 18);
            g2d.setFont(font);
            RenderingHints rh = new RenderingHints(
                    RenderingHints.KEY_ANTIALIASING,
                    RenderingHints.VALUE_ANTIALIAS_ON);
            rh.put(RenderingHints.KEY_RENDERING,
                    RenderingHints.VALUE_RENDER_QUALITY);
            g2d.setRenderingHints(rh);
            GradientPaint gp = new GradientPaint(0, 0,
                    new Color(200, 190, 220), 0, height / 2, new Color(245, 245, 220), true);
            g2d.setPaint(gp);
            g2d.fillRect(0, 0, width, height);
            g2d.setColor(new Color(145, 153, 160));
            Random r = new Random();
            int indx = Math.abs(r.nextInt(150));
            String captcha = String.copyValueOf(data[indx]);
//            req.getSession().setAttribute("captcha", captcha);
            System.out.println("captcha====" + captcha);
            int x = 0;
            int y = 0;
            for (int i = 0; i < data[indx].length; i++) {
                x += 10 + (Math.abs(r.nextInt()) % 15);
                y = 20 + Math.abs(r.nextInt()) % 20;
                g2d.drawChars(data[indx], i, 1, x, y);
            }
            g2d.dispose();
//            resp.setContentType("image/png");
//            OutputStream os = resp.getOutputStream();
            String subpathImg = "captcha/captcha_" + System.nanoTime() + "_" + indx + ".png";
            String path = Configuration.captcha_url + subpathImg;
            File outputfile = new File(path);
//            if (!outputfile.exists()) {
//                outputfile.createNewFile();
            ImageIO.write(bufferedImage, "png", outputfile);
//            }

            mapjson.put("path", Configuration.STATIC_URL + subpathImg);
            mapjson.put("code", captcha);

        } catch (IOException ex) {
            System.out.println("Exception Config Load Captcha:" + ex.getMessage());
        }
        return mapjson;
    }

    private static void loadUser() {
        try {
            if (path_static_user == null || path_static_user.isEmpty()) {
                path_static_user = Config.getParam("url", "log_static");
            }
            FileReader reader = new FileReader(path_static_user);

            JSONParser jsonParser = new JSONParser();
            JSONArray jsonArray = (JSONArray) jsonParser.parse(reader);
            for (int i = 0; i < jsonArray.size(); ++i) {
                JSONObject obj = (JSONObject) jsonArray.get(i);
                String username = "";
                String pass = "";
                if (obj.containsKey("name") && obj.containsKey("pass")) {
                    username = obj.get("name").toString();
                    pass = obj.get("pass").toString();
                }
                if (!username.isEmpty() && !pass.isEmpty()) {
                    String session = Utils.md5(username + "." + pass + ".");
                    mapSession.put(session, username);
                    mapTimeSession.put(session, 0l);
                }
            }
        } catch (Exception e) {
            System.out.println("Exception Config Load User:" + e.getMessage());
        }
    }

    static {
        init();
    }
}
