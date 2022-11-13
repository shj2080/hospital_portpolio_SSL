package util;

public class BoardPaging {
	
	//전체 페이지
	//전체 게시글 수 , 한 페이지 내 게시글 제한
	public static int totalPages(int totalPosts, int boardLimit) {
        return ((totalPosts - 1) / boardLimit) + 1;
    }

	//끝 페이지
	//현재 페이지, 한 페이지 내 선택가능한 페이지 제한, 전체 페이지
    public static int endPage(int currentPage, int displayPageNum, int totalPages) {
        int endPage = (((currentPage-1)/displayPageNum)+1) * displayPageNum;

        if(totalPages < endPage)
            endPage = totalPages;

        return endPage;
    }

    //시작페이지
    //현재 페이지, 한 페이지 선택가능한 페이지 제한
    public static int startPage(int currentPage, int displayPageNum) {
        return ((currentPage - 1) / displayPageNum) * displayPageNum + 1;
    }

    //이전 가능 여부
    public static boolean hasPrev(int startPage) {
        return (startPage == 1) ? false : true;
    }

    //다음 가능 여부
    public static boolean hasNext(int endPage, int totalPages) {
        return (endPage == totalPages) ? false : true;
    }
}
