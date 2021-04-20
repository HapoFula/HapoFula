package bitmap;

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.Queue;
import java.util.Scanner;

public class BitMap {

    static int[][] bitMap;
    static int n;
    static int m;

    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        int cases = Integer.parseInt(sc.nextLine());
        for (int i = 0; i < cases; i++) {
            ArrayList<int[]> whiteNodes = new ArrayList<int[]>();
            String mapSize = sc.nextLine();
            n = Integer.parseInt(mapSize.split(" ")[0]);
            m = Integer.parseInt(mapSize.split(" ")[1]);
            bitMap = new int[n][m];
            for (int j = 0; j < n; j++) {
                String line = sc.nextLine();
                for (int k = 0; k < m; k++) {
                    if (line.charAt(k) == '1') {
                        int[] tmp = {j, k};
                        whiteNodes.add(tmp);
                        bitMap[j][k] = -Integer.MAX_VALUE;
                    } else {
                        bitMap[j][k] = Integer.MAX_VALUE;
                    }
                }
            }
            for (int j = 0; j < whiteNodes.size(); j++) {
                calculateDistance(whiteNodes.get(j)[0], whiteNodes.get(j)[1]);
            }
            for (int x = 0; x < n; x++) {
                String result = "";
                for (int y = 0; y < m; y++) {
                    result += bitMap[x][y] + " ";
                }
                System.out.println(result);
            }
            sc.nextLine();
        }
    }

    public static void calculateDistance(int i, int j) {
        Queue<Integer> q = new LinkedList<Integer>();
        q.offer(i);
        q.offer(j);
        bitMap[i][j] = 0;
        while (!q.isEmpty()) {
            int x = q.poll();
            int y = q.poll();
            int dist = bitMap[x][y] + 1;
            //comprobando vecinos
            //derecha
            if (y + 1 < m && dist < bitMap[x][y + 1]) {
                q.offer(x);
                q.offer(y + 1);
                bitMap[x][y + 1] = dist;
            }
            //izquierda
            if (y - 1 >= 0 && dist < bitMap[x][y - 1]) {
                q.offer(x);
                q.offer(y - 1);
                bitMap[x][y - 1] = dist;
            }
            //arriba
            if (x - 1 >= 0 && dist < bitMap[x - 1][y]) {
                q.offer(x - 1);
                q.offer(y);
                bitMap[x - 1][y] = dist;
            }
            //debajo
            if (x + 1 < n && dist < bitMap[x + 1][y]) {
                q.offer(x + 1);
                q.offer(y);
                bitMap[x + 1][y] = dist;
            }

        }
    }

}
