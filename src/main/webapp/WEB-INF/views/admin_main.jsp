<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%@ page contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<!DOCTYPE html>
<html lang="ko">
<%@ include file="header.jsp" %>
<head>
    <meta charset="UTF-8" />
    <meta name="viewport" content="width=device-width, initial-scale=1" />
    <title>관리자 도서 관리</title>
    <link href="https://cdn.jsdelivr.net/npm/bootstrap@5.3.3/dist/css/bootstrap.min.css" rel="stylesheet" crossorigin="anonymous" />
    <style>
        body {
          font-family: "Segoe UI", "Malgun Gothic", sans-serif;
          background-color: #f8f9fa;
        }
        .navbar-brand {
          font-weight: bold;
          color: #0d6efd !important;
        }
        .table-book-cover {
            width: 60px;
            height: 90px;
            object-fit: cover;
            border-radius: 4px;
        }
        .table-actions {
            white-space: nowrap;
        }
    </style>
</head>
<body>
    <main class="container py-5">
        <div class="d-flex justify-content-between align-items-center mb-4">
            <h2 class="fw-semibold">📚 도서 관리 (관리자)</h2>
            <a href="/admin/addform" class="btn btn-primary">+ 도서 추가</a>
        </div>

        <div class="table-responsive">
            <table class="table table-striped table-hover align-middle">
                <thead class="table-dark">
                    <tr>
                        <th scope="col">#</th>
                        <th scope="col">책 표지</th>
                        <th scope="col">책 제목</th>
                        <th scope="col">저자</th>
                        <th scope="col">가격</th>
                        <th scope="col">관리</th>
                    </tr>
                </thead>
                <tbody>
                    <c:forEach var="book" items="${books}" varStatus="status">
                        <tr>
                            <th scope="row">${status.count}</th>
                            <td>
                                <a href="/admin/detail?id=${book.b_id}">
                                    <img src="${book.pic}" class="table-book-cover" alt="책 표지" />
                                </a>
                            </td>
                            <td>
                                <a href="/admin/detail?id=${book.b_id}" class="text-decoration-none text-dark">
                                    ${book.title}
                                </a>
                            </td>
                            <td>${book.author}</td>
                            <td>₩ <fmt:formatNumber value="${book.price}" type="number" groupingUsed="true" /></td>
                            <td class="table-actions">
                                <a href="/admin/edit/${book.b_id}" class="btn btn-outline-primary btn-sm me-2">수정</a>
                                <form action="/admin/delete/${book.b_id}" method="post" class="d-inline-block" onsubmit="return confirm('정말 삭제하시겠습니까?');">
                                    <input type="hidden" name="${_csrf.parameterName}" value="${_csrf.token}" />
                                    <button type="submit" class="btn btn-outline-danger btn-sm">삭제</button>
                                </form>
                            </td>
                        </tr>
                    </c:forEach>
                    <c:if test="${empty books}">
                        <tr>
                            <td colspan="6" class="text-center text-muted">등록된 도서가 없습니다.</td>
                        </tr>
                    </c:if>
                </tbody>
            </table>
        </div>
    </main>
<%@ include file="footer.jsp" %>
<script src="https://cdn.jsdelivr.net/npm/bootstrap@5.3.3/dist/js/bootstrap.bundle.min.js" crossorigin="anonymous"></script>
</body>
</html>