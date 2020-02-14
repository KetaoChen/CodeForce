import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.*;

public class Three_Paths_on_a_Tree_1294F {
    public static void main(String[] args) {
        Scanner in = new Scanner(new BufferedReader(new InputStreamReader(System.in)));
        int n = in.nextInt(); // Scanner has functions to read ints, longs, strings, chars, etc.
        List<Integer>[] graph = new List[n + 1];
        int[] d = new int[n + 1];
        for (int i = 0; i <= n; i++) {
            graph[i] = new ArrayList<>();
        }
        for (int i = 0; i < n - 1;i++) {
            int first = in.nextInt();
            int second = in.nextInt();
            graph[first].add(second);
            graph[second].add(first);
            d[first]++;
            d[second]++;
        }
        System.out.println("start");
        getRes(graph, d);
    }

    private static void getRes(List<Integer>[] graph, int[] degree) {

        System.out.println("start");
        int[] d = new int[degree.length];
        for (int i = 0; i < d.length; i++) {
            d[i] = degree[i];
        }

        Queue<Integer> q = new LinkedList<>();
        boolean[] visited = new boolean[graph.length];
        for (int i = 0; i < graph.length; i++) {
            if (d[i] == 1) {
                q.offer(i);
                visited[i] = true;
            }
        }

        if (q.size() == 2) {
            System.out.println(d.length - 1);
            int third = -1;
            for (int i = 0; i < d.length; i++) {
                if (d[i] > 1) {
                    third = i;
                    System.out.println(q.poll() + " " + q.poll() + " " + third);
                    return;
                }
            }
        }


        int[] mid = null;
        while (!q.isEmpty()) {
            int s = q.size();
            if (s == 1) {
                mid = new int[]{q.poll()};
                System.out.println("check1");
                break;
            }
            if (s == 2) {
                mid = new int[]{q.poll(), q.poll()};
                System.out.println("check2");
                break;
            }
            System.out.println("check");
            for (int i = 0; i < s; i++) {
                int cur = q.poll();
                for (int next : graph[cur]) {
                    d[next]--;
                    if (!visited[next] && d[next] == 1) {
                        q.offer(next);
                        visited[next] = true;
                    }
                }
            }
        }

        visited = new boolean[d.length];
        q = new LinkedList<>();
        List<Integer> res = new ArrayList<>();
        for (int start :  mid) {
            q.offer(start);
            visited[start] = true;
        }

        int[] dist = new int[d.length];
        int dis = 0;
        while (!q.isEmpty()) {
            int s = q.size();
            for (int i = 0; i < s; i++) {
                int cur = q.poll();
                if (degree[cur] == 1) {
                    res.add(cur);
                    dist[cur] = dis;
                }
                for (int next : graph[cur]) {
                    if (!visited[next]) {
                        q.offer(next);
                        visited[next] = true;
                    }
                }
            }
            dis++;
        }

        int sum = 0;
        for (int i = 1; i <= 3; i++) {
            sum += dist[res.get(res.size() - i)];
        }
        if (mid.length == 2) {
            sum++;
        }
        System.out.println(sum);
        System.out.println(res.get(res.size() - 1) + " " + res.get(res.size() - 2) + " " + res.get(res.size() - 3));
    }
}
