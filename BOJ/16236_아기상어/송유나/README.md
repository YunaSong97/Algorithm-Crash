![image](https://user-images.githubusercontent.com/33195517/185778844-d0771dc1-7532-4101-9afb-8f60ad3f1042.png)
</br>
BFS, Priority Queue
</br>
BFS로만 먹이를 찾으면 안되고 같은 depth에서 먹이를 x, y순으로 오름차순 정렬한 우선순위 큐에 넣어서 가장 순위가 높은 먹이를 먹어야한다.
1. 입력 받을 때, 출발 위치와 물고기 수를 같이 받는다.
2. 출발 위치에서 bfs를 한다.
3. 같은 depth에서 가능한 먹이를 PriorityQueue에 넣고, 다음 depth로 넘어가기 전에 priorityQueue에 먹이가 있으면 새로 출발점을 설정한다.
