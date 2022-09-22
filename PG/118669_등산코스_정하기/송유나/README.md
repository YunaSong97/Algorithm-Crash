### Dijkstra

일반적인 다익스트라와 차이점

1. 원래는 PriorityQueue를 사용해서 cost가 작은 것 부터 탐색한다. 하지만 이 문제는 다 탐색해서 최소인 것을 찾아야하기 때문에 PriorityQueue를 썼을 때 시간초과가 난다. → 그냥 Queue 사용
2. 

```java
/*일반적인 다익스트라는 어떤 노드까지 최솟값을 구할 때,
현재까지 구해둔 최솟값에 현재 노드에서 다음 노드로 가는 비용을 더한 값 중 작은 것을 택한다.

이 문제에서는 intensity가 최소인것을 저장하니까, 현재 노드에서 다음 노드로 가는 cost와
현재 노드의 구해둔 intensity(최소임을 보장) 중 작은 것으로 갱신하면 된다.
*/
if (intensity[next.e] > intensity[now.e] + next.cost)) {
    intensity[next.e] = intensity[now.e] + next.cost;
}

if (intensity[next.e] > Math.max(next.cost, intensity[now.e])) {
    intensity[next.e] = Math.max(next.cost, intensity[now.e]);
}
```

![image](https://user-images.githubusercontent.com/33195517/191171089-8173850c-471c-4acd-a911-63bc6353c7e7.png)
