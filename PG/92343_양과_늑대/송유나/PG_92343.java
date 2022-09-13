import java.util.*;

public class PG_92343 {
    class Node {
        int left, right, parent, wolf;
        int data;

        public Node(int data) {
            this.parent = -1;
            this.data = data;
            this.left = -1;
            this.right = -1;
        }

        public Node(int data, int parent, int wolf, int right, int left) {
            this.data = data;
            this.parent = parent;
            this.wolf = wolf;
            this.right = right;
            this.left = left;
        }
    }

    class Solution {
        static Node[] nodes;
        static Node[] nodesCopy;
        static int s = 0;
        static int answer = 0;

        public int solution(int[] info, int[][] edges) {

            int n = info.length;
            nodesCopy = new Node[n];
            nodes = new Node[n];

            for (int i = 0; i < n; i++) {
                nodesCopy[i] = new Node(info[i]);
            }

            //연결리스트 생성
            for (int[] edge : edges) {
                Node node = nodesCopy[edge[0]];
                if (node.left == -1) {
                    node.left = edge[1];
                } else {
                    node.right = edge[1];
                }
                nodesCopy[edge[1]].parent = edge[0];
            }

            //늑대 수 초기화
            nodesCopy[0].wolf = 0;
            Node root = nodesCopy[0];
            if (root.left != -1) {
                calWolf(nodesCopy[root.left]);
            }
            if (root.right != -1) {
                calWolf(nodesCopy[root.right]);
            }

            //늑대 수가 0이면 바로 양을 모으고, 늑대가 필요한 노드만 sheepList에 추가해서 순열 생성
            ArrayList<Integer> sheepList = new ArrayList<>();
            for (int i = 0; i < n; i++) {
                Node node = nodesCopy[i];
                if (node.data == 0) {
                    if (node.wolf != 0) {
                        sheepList.add(i);
                    } else {
                        s++;
                    }
                }
            }

            deepCopyNode();

            int size = sheepList.size();
            boolean[] visit = new boolean[size];
            int[] res = new int[size];
            perm(sheepList, visit, res, 0, size);

            return answer;
        }

        static void perm(ArrayList<Integer> sheepList, boolean[] visit, int[] res, int depth, int n) {
            if (depth == n) {
                int wolf = 0;
                int sheep = s;

                for (int i = 0; i < n; i++) {
                    Node now = nodes[sheepList.get(res[i])];

                    wolf += countWolf(now);
                    if (wolf < sheep) {
                        sheep++;
                        resetWolf(now);
                    } else {
                        answer = Math.max(answer, sheep);

                        deepCopyNode();
                        return;
                    }
                }

                answer = Math.max(answer, sheep);

                deepCopyNode();
                return;
            }

            for (int i = 0; i < n; i++) {
                if (visit[i]) {
                    continue;
                }

                visit[i] = true;
                res[depth] = i;
                perm(sheepList, visit, res, depth + 1, n);
                visit[i] = false;
            }
        }

        static void deepCopyNode() {
            for (int j = 0; j < nodesCopy.length; j++) {
                nodes[j] = new Node(nodesCopy[j].data, nodesCopy[j].parent, nodesCopy[j].wolf, nodesCopy[j].right, nodesCopy[j].left);
            }
        }

        static int countWolf(Node node) {
            int cnt = 0;

            while (node.parent != -1) {
                if (nodes[node.parent].data == 1) {
                    cnt++;
                }

                node = nodes[node.parent];
            }

            return cnt;
        }

        static void resetWolf(Node node) {
            while (node.parent != -1) {
                if (nodes[node.parent].data == 1) {//늑대면 0
                    nodes[node.parent].data = 0;
                }

                node = nodes[node.parent];
            }
        }

        static void calWolf(Node node) {
            if (node.data == 0) {
                node.wolf = nodesCopy[node.parent].wolf;
            } else {
                node.wolf = nodesCopy[node.parent].wolf + 1;
            }

            if (node.left == -1 && node.right == -1) {
                return;
            }

            if (node.left != -1) {
                calWolf(nodesCopy[node.left]);
            }
            if (node.right != -1) {
                calWolf(nodesCopy[node.right]);
            }
        }
    }
}

