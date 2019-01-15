import java.util.*;

public class Main {


    public static void main(String[] args) {

        Scanner in = new Scanner(System.in);
        String line;
        String[] info;
        String mtaName;
        int mtaRecords;
        List<Node> mtaList;
        Set<String> routePath = new HashSet<>();
        int scenarion = 1;
        while (in.hasNextLine()) {
            line = in.nextLine().trim();
            if (line.isEmpty())
                continue;
            System.out.println("Scenario # " + scenarion++);
            int m = Integer.parseInt(line);
            Map<String, List<Node>> mtaTable = new HashMap<>(m);
            while (m-- != 0) {
                line = in.nextLine().trim();
                info = line.split("\\s+");
                mtaName = info[0];
                mtaRecords = Integer.parseInt(info[1]);
                mtaList = new ArrayList<>(mtaRecords);
                while (mtaRecords-- != 0) {
                    line = in.nextLine().trim();
                    info = line.split("\\s++");
                    mtaList.add(new Node(info[0], info[1], info[2], info[3], info[4]));
                }
                mtaTable.put(mtaName, mtaList);
            }
            line = in.nextLine();
            int n = Integer.parseInt(line);
            Node node = new Node();

            for (int i = 1; i <= n; i++) {
                System.out.printf("%d -- ", i);
                line = in.nextLine();
                info = line.split("\\s+");
                node.destination = info[0];
                node.c = info[1];
                node.admd = info[2];
                node.prmd = info[3];
                node.o = info[4];
                routePath.clear();
                boolean processing = true;
                boolean found;
                List<Node> mta;
                while (processing) {
                    if (routePath.contains(node.destination)) {
                        System.out.println("circular routing detected by " + node.destination);
                        break;
                    } else {
                        routePath.add(node.destination);
                    }

                    mta = mtaTable.get(node.destination);
                    if (mta == null) {
                        System.out.println("unable to route at " + node.destination);
                        break;
                    }
                    found = false;
                    for (Node destination : mta) {
                        if (destination.same(node)) {
                            if (destination.destination.equals(node.destination)) {
                                System.out.println("delivered to " + node.destination);
                                processing = false;
                            } else {
                                node.destination = destination.destination;
                            }
                            found = true;
                            break;
                        }
                    }
                    if (!found) {
                        System.out.println("unable to route at " + node.destination);
                        break;
                    }
                }
            }
            System.out.println();
        }
    }

    public static class Node {
        public String destination;
        public String c;
        public String admd;
        public String prmd;
        public String o;

        public Node() {

        }

        public Node(String destination, String c, String admd, String prmd, String o) {
            this.destination = destination;
            this.c = c;
            this.admd = admd;
            this.prmd = prmd;
            this.o = o;
        }

        public boolean same(Node n) {
            if (!c.equals(n.c) && !c.equals("*"))
                return false;
            if (!admd.equals(n.admd) && !admd.equals("*"))
                return false;
            if (!prmd.equals(n.prmd) && !prmd.equals("*"))
                return false;
            return o.equals(n.o) || o.equals("*");
        }
    }
}
