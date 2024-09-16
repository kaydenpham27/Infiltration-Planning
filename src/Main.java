import obstacles.LocatableObstacle;
import obstacles.Obstacle;

import javax.rmi.ssl.SslRMIClientSocketFactory;
import java.lang.reflect.Array;
import java.util.ArrayList;
import java.util.HashMap;
import obstacles.*;
import common.Map;
import common.Location;

public class Main {
    private static HashMap<String, ArrayList<String>> parseArgs(String[] args) {
        HashMap<String, ArrayList<String>> parsedArgs = new HashMap<>();
        ArrayList<String> argValues = null;
        for (String arg: args) {
            if (arg.startsWith("-")) {
                argValues = new ArrayList<>();
                parsedArgs.put(arg, argValues);
            } else if (argValues != null) {
                argValues.add(arg);
            }
        }
        return parsedArgs;
    }
    private static String stripParentheses(String s) {
        return s.substring(1, s.length() - 1);
    }
    private static ArrayList<Obstacle> parseObstacles(HashMap<String, ArrayList<String>> parsedArgs) {
        ArrayList<Obstacle> obstacles = new ArrayList<>();
        for (ObstacleType type : ObstacleType.values()) {
            String key = "-" + type.getArgumentName();
            ArrayList<String> args = parsedArgs.get(key);
            if (args == null) return obstacles;
            for (String arg : args) {
                String cleanedArg = stripParentheses(arg);
                Obstacle obstacle = switch (type) {
                    case GUARD -> Guard.parse(cleanedArg);
                    case FENCE -> Fence.parse(cleanedArg);
                    case SENSOR -> Sensor.parse(cleanedArg);
                    case CAMERA -> Camera.parse(cleanedArg);
                };
                obstacles.add(obstacle);
            }
        }
        return obstacles;
    }
    public static void main(String[] args) {
       HashMap<String, ArrayList<String>> parsedArgs = parseArgs(args);
       ArrayList<Obstacle> obstacles = parseObstacles(parsedArgs);
       Map map = new Map(obstacles);

       String startArg = stripParentheses(parsedArgs.get("-start").getFirst());
       String targetArg = stripParentheses(parsedArgs.get("-target").getFirst());
       Location start = Location.parse(startArg);
       Location target = Location.parse(targetArg);

       System.out.println(map.getSolvedMap(start, target));
    }
}



//java .\Main.java -start "(0,7)" -target "(7,2)" -g "(6,2)" -f "(2,0,2,5)" "(0,3,4,3)" "(6,3,7,3)" -s "(1,4,2.5)" -c "(4,2,w)" "(4,7,e)"

