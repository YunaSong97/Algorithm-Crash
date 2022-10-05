![image](https://user-images.githubusercontent.com/33195517/193980181-3308aede-5efd-4176-8d6e-e7ce8d779e4f.png)

###Implementation

1. Fire를 fires 배열의 인덱스 (r-1) * n + (c-1) 에 저장한다.
2. dr, dc를 인덱스에 맞게 설정해서 다음 움직이는 위치를 구하고, 범위를 조절한다.
3. temp에 넣어놓고 fires를 비우고, temp를 다시 fires에 추가해 Fire를 옮긴다.
4. 2개 이상이면, 질량합과 속도합을 구하고 질량합이 0이면 없앤다.
5. stream.allMatch으로 모든 방향이 짝수 또는 홀수인지 확인하고, fires를 clear해주고 나눠진 4개 Fire를 추가한다.
