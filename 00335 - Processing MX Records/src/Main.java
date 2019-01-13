import java.util.*;

public class Main {

    public static void main(String[] args) {
        Map<String, Boolean> serverStatus = new HashMap<>();
        Scanner in = new Scanner(System.in);
        String line = in.nextLine();
        String[] info;
        List<String> serverInfoList = null;
        int priority;
        String redirectServer;
        int n = Integer.parseInt(line);
        Node redirectRules = new Node();
        for (int i = 0; i < n; i++) {
            line = in.nextLine().trim();
            info = line.split("\\s+");
            if (info.length == 3) {
                serverInfoList = new ArrayList<>(Arrays.asList(info[0].split("\\.")));
                priority = Integer.parseInt(info[1]);
                redirectServer = info[2];
            } else {
                priority = Integer.parseInt(info[0]);
                redirectServer = info[1];
            }

            redirectRules.addNewRedirectRule(serverInfoList, priority, redirectServer);

            if (!serverStatus.containsKey(redirectServer)) {
                serverStatus.put(redirectServer, true);
            }
        }
        boolean done = false;
        while (!done) {
            line = in.nextLine();
            info = line.split("\\s+");
            char command = info[0].charAt(0);
            switch (command) {
                case 'A':
                    serverInfoList = new ArrayList<>(Arrays.asList(info[1].split("\\.")));
                    PriorityQueue<Pair> priorityQueue = redirectRules.getRedirects(serverInfoList);
                    Pair[] priorityArray = priorityQueue.toArray(new Pair[priorityQueue.size()]);
                    Arrays.sort(priorityArray, priorityQueue.comparator());
                    String found = null;
                    for (Pair p : priorityArray) {
                        //System.out.println(p.getLeft() + " " + p.getRight());
                        if (serverStatus.containsKey(p.getRight())) {
                            if (serverStatus.get(p.getRight())) {
                                found = p.getRight();
                                break;
                            }
                        }
                    }
                    System.out.print(info[1] + " =>");
                    if (found != null) {
                        System.out.print(" " + found);
                    }
                    System.out.println();
                    break;
                case 'D':
                    serverStatus.put(info[1], false);
                    break;
                case 'U':
                    serverStatus.put(info[1], true);
                    break;
                case 'X':
                    done = true;
                    break;
            }
        }
    }

    public static class Node {
        public Map<String, Node> listOfEndpoints;
        public PriorityQueue<Pair> redirectServerList;

        public Node() {
            listOfEndpoints = new HashMap<>();
            redirectServerList = new PriorityQueue<>(1, new Pair.PairComparator());
        }

        public void addNewRedirectRule(List<String> serverInfoList, int priority, String redirectServer) {
            if (serverInfoList.size() == 0) {
                redirectServerList.add(new Pair(priority, redirectServer));
            } else {
                String info = serverInfoList.get(serverInfoList.size() - 1);
                Node node;
                if (listOfEndpoints.containsKey(info)) {
                    node = listOfEndpoints.get(info);
                } else {
                    node = new Node();
                    listOfEndpoints.put(info, node);
                }
                node.addNewRedirectRule(serverInfoList.subList(0, serverInfoList.size() - 1), priority, redirectServer);
            }
        }

        public PriorityQueue<Pair> getRedirects(List<String> serverInfoList) {
            if (serverInfoList.size() == 0) {
                return redirectServerList;
            } else {
                String info = serverInfoList.get(serverInfoList.size() - 1);
                PriorityQueue<Pair> res = new PriorityQueue<>(1, new Pair.PairComparator());
                if (listOfEndpoints.containsKey(info)) {
                    res.addAll(listOfEndpoints.get(info).getRedirects(serverInfoList.subList(0, serverInfoList.size() - 1)));
                }
                if (listOfEndpoints.containsKey("*")) {
                    res.addAll(listOfEndpoints.get("*").redirectServerList);
                }
                return res;
            }
        }
    }

    public static class Pair {

        private final Integer left;
        private final String right;

        public Pair(Integer left, String right) {
            this.left = left;
            this.right = right;
        }

        public Integer getLeft() {
            return left;
        }

        public String getRight() {
            return right;
        }

        @Override
        public int hashCode() {
            return left.hashCode() ^ right.hashCode();
        }

        @Override
        public boolean equals(Object o) {
            if (!(o instanceof Pair)) return false;
            Pair pairo = (Pair) o;
            return this.left.equals(pairo.getLeft()) &&
                    this.right.equals(pairo.getRight());
        }

        public static class PairComparator implements Comparator<Pair> {
            public int compare(Pair a, Pair b) {
                return a.getLeft() - b.getLeft();
            }
        }
    }
}
