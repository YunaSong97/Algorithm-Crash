![image](https://user-images.githubusercontent.com/33195517/185920671-2709a5c1-820c-4193-aa3c-11a5075ec658.png)

왼쪽으로 회전은 인덱스-1 이다. 배열 크기를 고려해 +4 해주고 %4해서 (x+3)%4 를 인덱스로 사용한다.</br>
후진은 인덱스 +2 인데, 마찬가지로 %4를 해준다.</br>
배열 값을 0에서 2로 바꿔서 청소가 안된 상태에서 청소를 했다고 표시하고, 이때 ans++ 해준다.</br>
큐가 비면 ans에 최댓값을 저장한다.
