# 15683번: 감시

| 메모리 | 시간 |
| --- | --- |
| 23436 KB | 252ms |

완전탐색 문제로 cctv를 리스트에 저장해서 cctv마다 가능한 모든 방향을 부분집합을 탐색하는 방법으로 탐색해서 cctv가 감시할 수 있는 지역이 0 일 때 7 + depth로 값을 바꿔준 후, 최종적으로 depth가 리스트의 사이즈와 같아질때 map에 남아있는 0의 갯수가 사각지대이므로 사각지대의 최솟값을 비교해 answer에 저장한다.