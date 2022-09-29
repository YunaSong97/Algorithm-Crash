1. 각 cctv가 볼수 있는 영역들을 모두 구하여 방향에 맞게 배열에 저장한다</br>
2. cctv의 입력 요소 각각을 순서대로 방향에 맞게 방문한다. <br/>
3. 그 방향에 벽이나 범위를 벗어난다면 반복문을 탈출하고 방문한 자리들을 지도에 업데이트 한다.</br>
4. 모든 방향을 확인했다면 변경된 지도를 다음 dfs 인자로 넘긴다</br>
5. dfs count가 cctv의 갯수가 같을떄 최소값을 구해준다</br>
<img width="196" alt="감시" src="https://user-images.githubusercontent.com/68943993/193022894-fd1889d9-fb1e-46d0-b2d8-5f8ffb6636b0.PNG">
