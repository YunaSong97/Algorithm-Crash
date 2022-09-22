### **HashMap**

**Record 클래스**

- in/out 시간, 차번호, 나간거 체크, 총 시간
- calTime : 나간시간에서 들어온 시간 빼서 총 몇분 주차했는지 구함
- calFee : 돈계산
- compareTo : 차번호로 오름차순 정렬

1. IN 인데, hashMap에 있는 차번호면 in시간 갱신, check=false
2. IN 인데, hashMap에 없는 차번호면 추가
3. OUT 이면, out시간 갱신, calTime 해서 총 시간 계산
4. Record ArrayList에서 check=false(계산 안된 차)는 out시간을 23:59로 해주고 총 시간 계산.
5. 정렬해서 돈계산

![https://user-images.githubusercontent.com/33195517/190081773-da825fd7-e6e6-4971-8591-7ef422cdab04.png](https://user-images.githubusercontent.com/33195517/190081773-da825fd7-e6e6-4971-8591-7ef422cdab04.png)
