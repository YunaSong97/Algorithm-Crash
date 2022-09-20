import java.util.*;

class Node {
    int e, cost;

    Node(int e, int cost) {
        this.e = e;
        this.cost = cost;
    }
}

class PG_118669 {
    static ArrayList<ArrayList<Node>> graph;
    static int[] answer;
    public int[] solution(int n, int[][] paths, int[] gates, int[] summits) {
        answer = new int[2];
        Arrays.fill(answer, Integer.MAX_VALUE);
        graph = new ArrayList<>();

        for (int i = 0; i < n + 1; i++) {
            graph.add(new ArrayList<>());
        }
        for (int i = 0; i < paths.length; i++) {
            graph.get(paths[i][0]).add(new Node(paths[i][1], paths[i][2]));
            graph.get(paths[i][1]).add(new Node(paths[i][0], paths[i][2]));
        }

        dijkstra(n, gates, summits);

        return answer;
    }

    public void dijkstra(int n, int[] gates, int[] summits) {
        int[] intensity = new int[n + 1];
        Arrays.fill(intensity, Integer.MAX_VALUE);

        Queue<Node> queue = new ArrayDeque<>();

        for (int gate : gates) {
            queue.add(new Node(gate, 0));
            intensity[gate] = 0;
        }

        while (!queue.isEmpty()) {
            Node now = queue.poll();

            if (isSummit(now.e, summits)) {
                continue;
            }

            if (intensity[now.e] > now.cost) {
                continue;
            }

            for (int i = 0; i < graph.get(now.e).size(); i++) {
                Node next = graph.get(now.e).get(i);
              
                if (isGate(next.e, gates)) {
                    continue;
                }
                if (intensity[next.e] > Math.max(next.cost, intensity[now.e])) {
                    intensity[next.e] = Math.max(next.cost, intensity[now.e]);
                    queue.add(new Node(next.e, intensity[next.e]));
                }
            }
        }

        Arrays.sort(summits);
        for (int summit : summits) {
            if (intensity[summit] < answer[1]) {
                answer[0] = summit;
                answer[1] = intensity[summit];
            }
        }
    }

    static boolean isSummit(int e, int[] summits) {
        for (int summit : summits) {
            if (summit == e) {
                return true;
            }
        }

        return false;
    }

    static boolean isGate(int e, int[] gates) {
        for (int gate : gates) {
            if (gate == e) {
                return true;
            }
        }

        return false;
    }
}
