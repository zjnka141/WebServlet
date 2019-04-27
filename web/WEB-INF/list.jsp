<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<!doctype html>
<html lang="vi">

<head>
    <title>Title</title>
    <!-- Required meta tags -->
    <meta charset="utf-8">
    <meta name="viewport" content="width=device-width, initial-scale=1, shrink-to-fit=no">
    <!-- Bootstrap CSS -->
    <link rel="stylesheet" href="https://stackpath.bootstrapcdn.com/bootstrap/4.3.1/css/bootstrap.min.css">
    <link rel="stylesheet" href="https://maxcdn.bootstrapcdn.com/font-awesome/4.7.0/css/font-awesome.min.css">
</head>

<body>


<div class="jumbotron">
    <div class="container">
        <form action="/customer">
        <div class="row">
            <div class="col-md-6">
                    <input type="hidden" name="action" value="search">
                    <input class="form-control" type="number" name="id" placeholder="Tìm kiếm khách hàng theo ID...">
            </div>
            <div class="col-md-2">
                <button class="btn btn-secondary btn-block" type="submit">
                    <i class="fa fa-search"></i> Tìm kiếm
                </button>
            </div>

        </div>
        </form>
        <c:if test="${notFound}">
            <br>
            <p class="text-danger">Không tìm thấy khách hàng có ID là ${idSearch}</p>
        </c:if>
    </div>
</div>
<div class="container">
    <div class="row">
        <div class="col-md-8">
            <h3 class="text-center text-success">Danh sách khách hàng</h3>
            <table class="table table-striped">
                <thead>
                <tr>
                    <th>ID</th>
                    <th>Tên</th>
                    <th>Email</th>
                    <th>Địa chỉ</th>
                    <th>Options</th>
                </tr>
                </thead>
                <tbody>
                <c:forEach items="${customerList}" var="customer">
                    <c:choose>
                        <c:when test="${editId==customer.getId()}">
                            <tr>
                                <form action="/customer" method="POST">
                                    <input type="hidden" name="action" value="edit">
                                    <input type="hidden" name="id" value="${customer.getId()}">
                                <td>${customer.getId()}</td>
                                <td><input type="text" class="form-control" name="name" value="${customer.getName()}"></td>
                                <td><input type="text" class="form-control" name="email" value="${customer.getEmail()}"></td>
                                <td><input type="text" class="form-control" name="address" value="${customer.getAddress()}"></td>
                                <td style="width:180px;">
                                    <button class="btn btn-success text-white" type="submit">
                                        <i class="fa fa-save"></i>Lưu
                                    </button>
                                    <a href="/customer" style="color:white;" class="btn btn-danger text-white">
                                        <i class="fa fa-times"></i>
                                        Hủy</a>
                                </td>
                                </form>
                            </tr>
                        </c:when>
                        <c:otherwise>
                            <tr>
                                <td>${customer.getId()}</td>
                                <td>${customer.getName()}</td>
                                <td>${customer.getEmail()}</td>
                                <td>${customer.getAddress()}</td>
                                <td style="width:180px;">
                                    <a href="/customer?action=edit&id=${customer.getId()}" class="btn btn-warning text-white">
                                        <i class="fa fa-pencil"></i>
                                        Sửa</a>

                                    <a href="/customer?action=delete&id=${customer.getId()}" class="btn btn-danger text-white"
                                       onclick="return confirm('Bạn muốn xóa khách hàng này?')">
                                        <i class="fa fa-trash"></i>
                                        Xóa</a>
                                </td>
                            </tr>
                        </c:otherwise>
                    </c:choose>
                </c:forEach>
                </tbody>
            </table>
        </div>
        <div class="col-md-4">
            <form action="/customer" method="POST">
                <h3 class="text-center text-danger">Thêm nhân viên</h3>
                <input type="hidden" name="action" value="create">
                <div class="form-group">
                    <input type="text" class="form-control" name="name" placeholder="Tên khách hàng...">
                </div>
                <div class="form-group">
                    <input type="email" class="form-control" name="email" placeholder="Email khách hàng...">
                </div>
                <div class="form-group">
                    <input type="text" class="form-control" name="address" placeholder="Địa chỉ khách hàng...">
                </div>
                <button type="submit" class="btn btn-outline-success btn-block">Thêm</button>
            </form>
        </div>
    </div>
</div>
<!-- Optional JavaScript -->
<!-- jQuery first, then Popper.js, then Bootstrap JS -->
<script src="https://code.jquery.com/jquery-3.3.1.slim.min.js"></script>
<script src="https://cdnjs.cloudflare.com/ajax/libs/popper.js/1.14.7/umd/popper.min.js"></script>
<script src="https://stackpath.bootstrapcdn.com/bootstrap/4.3.1/js/bootstrap.min.js"></script>
</body>

</html>
