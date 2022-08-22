![image](https://user-images.githubusercontent.com/33195517/185825435-1e4fe430-803b-43ac-8599-dcecbd2f93e1.png)

구현
</br>
1. 좋아하는 학생이 많음 -> 빈칸이 많음 -> 행이 작음 -> 열이 작음 순으로 Priority Queue를 만든다.
2. 각 학생이 좋아하는 학생 배열 likeList를 만든다.
3. placeStudent() 함수
첫번째 학생은 무조건 map[1][1]에 배치된다. (i=0)</br>
그다음 학생부터는 모든 자리에 대해 checkSide 함수를 수행한다.</br>
학생이 앉을 자리는 priority queue에서 가장 우선순위가 높은 자리이므로 poll해주고 큐를 비워준다.</br>
4. checkSide(node, student)</br>
node의 사방을 탐색해서 0이면 빈자리, likeList[student]에 있는 숫자면 좋아하는 학생 수를 증가시켜 priority queue에 넣는다.</br>
5. calScore()</br>
모든 자리에 앉은 학생에 대해 사방에 좋아하는 학생이 몇명 앉았는지 세고, 점수를 더해준다.
