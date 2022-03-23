<%@ page language="java" contentType="text/html; charset=UTF-8"
         pageEncoding="UTF-8"%>
<%@ taglib prefix = "c" uri = "http://java.sun.com/jsp/jstl/core" %>

<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01//EN" "http://www.w3.org/TR/html4/strict.dtd">
<html lang="en">
<head>
    <meta charset="UTF-8">
    <title>Employee</title>
    <link rel="stylesheet" href="https://cdn.jsdelivr.net/npm/bootstrap@4.3.1/dist/css/bootstrap.min.css" integrity="sha384-ggOyR0iXCbMQv3Xipma34MD+dH/1fQ784/j6cY/iJTQUOhcWr7x9JvoRxT2MZw1T" crossorigin="anonymous">
    <script src="https://code.jquery.com/jquery-3.3.1.slim.min.js" integrity="sha384-q8i/X+965DzO0rT7abK41JStQIAqVgRVzpbzo5smXKp4YfRvH+8abtTE1Pi6jizo" crossorigin="anonymous"></script>
    <script src="https://cdn.jsdelivr.net/npm/popper.js@1.14.7/dist/umd/popper.min.js" integrity="sha384-UO2eT0CpHqdSJQ6hJty5KVphtPhzWj9WO1clHTMGa3JDZwrnQq4sF86dIHNDz0W1" crossorigin="anonymous"></script>
    <script src="https://cdn.jsdelivr.net/npm/bootstrap@4.3.1/dist/js/bootstrap.min.js" integrity="sha384-JjSmVgyd0p3pXB1rRibZUAYoIIy6OrQ6VrjIEaFf/nJGzIxFDsf4x0xIM+B07jRM" crossorigin="anonymous"></script>
    <link rel="stylesheet" href="https://cdnjs.cloudflare.com/ajax/libs/font-awesome/6.1.0/css/all.min.css" integrity="sha512-10/jx2EXwxxWqCLX/hHth/vu2KY3jCF70dCQB8TSgNjbCVAC/8vai53GfMDrO2Emgwccf2pJqxct9ehpzG+MTw==" crossorigin="anonymous" referrerpolicy="no-referrer" />
</head>
<body>
<nav class="navbar navbar-expand-lg navbar-light bg-light">
    <a class="navbar-brand" href="#">Navbar</a>
    <button class="navbar-toggler" type="button" data-toggle="collapse" data-target="#navbarSupportedContent" aria-controls="navbarSupportedContent" aria-expanded="false" aria-label="Toggle navigation">
        <span class="navbar-toggler-icon"></span>
    </button>

    <div class="collapse navbar-collapse" id="navbarSupportedContent">
        <ul class="navbar-nav mr-auto">
            <li class="nav-item active">
                <a class="nav-link" href="/employee/home">Department <span class="sr-only">(current)</span></a>
            </li>
            <li class="nav-item">
                <a class="nav-link" href="#">Link</a>
            </li>
            <li class="nav-item dropdown">
                <a class="nav-link dropdown-toggle" href="#" id="navbarDropdown" role="button" data-toggle="dropdown" aria-haspopup="true" aria-expanded="false">
                    Dropdown
                </a>
                <div class="dropdown-menu" aria-labelledby="navbarDropdown">
                    <a class="dropdown-item" href="#">Action</a>
                    <a class="dropdown-item" href="#">Another action</a>
                    <div class="dropdown-divider"></div>
                    <a class="dropdown-item" href="#">Something else here</a>
                </div>
            </li>
            <li class="nav-item">
                <a class="nav-link disabled" href="#">Disabled</a>
            </li>
        </ul>
        <form action="search-employee" method="post" class="form-inline my-2 my-lg-0">
            <input class="form-control mr-sm-2" name="txtSearch" type="search" placeholder="Search" aria-label="Search">
            <button name="action" value="search" class="btn btn-outline-success my-2 my-sm-0" type="submit">Search</button>
        </form>
    </div>
</nav>
<br/>
<div class="container">
    <div class="row">
        <div class="col-lg-3">
            <h2>Create employee</h2>
            <form  action="list-employee" method="post" >
                <div class="form-group">
                    <label for="exampleInputFullName">Full name</label>
                    <input type="text" name ="fullname" class="form-control" id="exampleInputFullName" placeholder="Enter full name" required>
                </div>
                <div class="form-group">
                    <label for="exampleInputAddress">Address</label>
                    <input type="text" name ="address" class="form-control" id="exampleInputAddress" placeholder="Enter Address" required>
                </div>
                <div class="form-group">
                    <label for="exampleInputPhoneNumber">Phone number</label>
                    <input type="text" name ="phoneNumber" class="form-control" id="exampleInputPhoneNumber" placeholder="Enter phone number" required>
                </div>
                <div class="form-group">
                    <label for="exampleInputEmail1">Email</label>
                    <input type="text" name ="email" class="form-control" id="exampleInputEmail1" placeholder="Enter email" required>
                </div>
                <div class="form-group">
                    <label>Departments :</label>
                    <select name="dept" class="custom-select" required>
                        <option selected>Select department</option>
                        <c:forEach items="${dept}" var="dept">
                            <option value="${dept.getDept_id()}">${dept.getDept_name()}</option>
                        </c:forEach>
                    </select>
                </div>

                <button type="submit" name="action" value="create" class="btn btn-primary">Create</button>
            </form>
            <hr/>
            <h2>Update employee</h2>
            <form  action="list-employee" method="post" >
                <div class="form-group">
                    <label for="exampleInputFullName">Full name</label>
                    <input type="hidden" name ="id" class="form-control"  value="${detailEmployee.getId()}" >
                    <input type="text" name ="fullname" class="form-control"  value="${detailEmployee.getFullName()}" required>
                </div>
                <div class="form-group">
                    <label for="exampleInputAddress">Address</label>
                    <input type="text" name ="address" class="form-control" value="${detailEmployee.getAddress()}"  required>
                </div>
                <div class="form-group">
                    <label for="exampleInputPhoneNumber">Phone number</label>
                    <input type="text" name ="phoneNumber" class="form-control"  value="${detailEmployee.getPhoneNumber()}" required>
                </div>
                <div class="form-group">
                    <label for="exampleInputEmail1">Email</label>
                    <input type="text" name ="email" class="form-control" value="${detailEmployee.getEmail()}" required>
                </div>
                <div class="form-group">
                    <label>Departments :</label>
                    <select name="dept" class="custom-select" required>
                        <option selected>Select department</option>
                        <c:forEach items="${dept}" var="dept">
                            <option value="${dept.getDept_id()}" ${dept.getDept_id() == detailEmployee.department.getDept_id() ? 'selected="selected"' : ''}>${dept.getDept_name()}</option>
                        </c:forEach>
                    </select>
                </div>

                <button type="submit" name="action" value="update" class="btn btn-primary">Update</button>
            </form>
        </div>
        <div class="col-lg-9">
            <table class="table table-striped">
                <thead>
                <tr>
                    <th scope="col">#</th>
                    <th scope="col">Name</th>
                    <th scope="col">Address</th>
                    <th scope="col">Phone number</th>
                    <th scope="col">Email</th>
                    <th scope="col">Department</th>
                    <th scope="col">Action</th>
                </tr>
                </thead>
                <tbody>
                <c:forEach items="${epl}" var="employee">
                    <tr>
                        <th scope="row">${employee.getId()}</th>
                        <td>${employee.getFullName()}</td>
                        <td>${employee.getAddress()}</td>
                        <td>${employee.getPhoneNumber()}</td>
                        <td>${employee.getEmail()}</td>
                        <td>${employee.department.getDept_name()}</td>
                        <td><a href="?id=${employee.getId()}" class="btn btn-outline-info" >
                            <i class="fa-solid fa-pen"></i>
                        </a>
                            <form action="list-employee" method="post" style="display: inline-block;">
                                <input type="hidden" name="id" value="${employee.getId()}"/>
                                <button name="action" value="delete" class="btn btn-outline-danger" >
                                    <i class="fa-solid fa-trash-can"></i>
                                </button>
                            </form>

                        </td>
                    </tr>
                </c:forEach>
                </tbody>
            </table>
            <ul class="pagination justify-content-end">

                <c:forEach var="i" begin="1" end="5">
                    <li class="page-item ${i == currentPage ? "active" : ""  }"><a class="page-link" href="list-employee?page=${i}">${i}</a></li>
                </c:forEach>

            </ul>
        </div>
    </div>
</div>



<!-- Footer -->
<footer class="text-center text-lg-start bg-light text-muted">
    <!-- Section: Social media -->
    <section
            class="d-flex justify-content-center justify-content-lg-between p-4 border-bottom"
    >
        <!-- Left -->
        <div class="me-5 d-none d-lg-block">
            <span>Get connected with us on social networks:</span>
        </div>
        <!-- Left -->

        <!-- Right -->
        <div>
            <a href="" class="me-4 text-reset">
                <i class="fab fa-facebook-f"></i>
            </a>
            <a href="" class="me-4 text-reset">
                <i class="fab fa-twitter"></i>
            </a>
            <a href="" class="me-4 text-reset">
                <i class="fab fa-google"></i>
            </a>
            <a href="" class="me-4 text-reset">
                <i class="fab fa-instagram"></i>
            </a>
            <a href="" class="me-4 text-reset">
                <i class="fab fa-linkedin"></i>
            </a>
            <a href="" class="me-4 text-reset">
                <i class="fab fa-github"></i>
            </a>
        </div>
        <!-- Right -->
    </section>
    <!-- Section: Social media -->

    <!-- Section: Links  -->
    <section class="">
        <div class="container text-center text-md-start mt-5">
            <!-- Grid row -->
            <div class="row mt-3">
                <!-- Grid column -->
                <div class="col-md-3 col-lg-4 col-xl-3 mx-auto mb-4">
                    <!-- Content -->
                    <h6 class="text-uppercase fw-bold mb-4">
                        <i class="fas fa-gem me-3"></i>Company name
                    </h6>
                    <p>
                        Here you can use rows and columns to organize your footer content. Lorem ipsum
                        dolor sit amet, consectetur adipisicing elit.
                    </p>
                </div>
                <!-- Grid column -->

                <!-- Grid column -->
                <div class="col-md-2 col-lg-2 col-xl-2 mx-auto mb-4">
                    <!-- Links -->
                    <h6 class="text-uppercase fw-bold mb-4">
                        Products
                    </h6>
                    <p>
                        <a href="#!" class="text-reset">Angular</a>
                    </p>
                    <p>
                        <a href="#!" class="text-reset">React</a>
                    </p>
                    <p>
                        <a href="#!" class="text-reset">Vue</a>
                    </p>
                    <p>
                        <a href="#!" class="text-reset">Laravel</a>
                    </p>
                </div>
                <!-- Grid column -->

                <!-- Grid column -->
                <div class="col-md-3 col-lg-2 col-xl-2 mx-auto mb-4">
                    <!-- Links -->
                    <h6 class="text-uppercase fw-bold mb-4">
                        Useful links
                    </h6>
                    <p>
                        <a href="#!" class="text-reset">Pricing</a>
                    </p>
                    <p>
                        <a href="#!" class="text-reset">Settings</a>
                    </p>
                    <p>
                        <a href="#!" class="text-reset">Orders</a>
                    </p>
                    <p>
                        <a href="#!" class="text-reset">Help</a>
                    </p>
                </div>
                <!-- Grid column -->

                <!-- Grid column -->
                <div class="col-md-4 col-lg-3 col-xl-3 mx-auto mb-md-0 mb-4">
                    <!-- Links -->
                    <h6 class="text-uppercase fw-bold mb-4">
                        Contact
                    </h6>
                    <p><i class="fas fa-home me-3"></i> New York, NY 10012, US</p>
                    <p>
                        <i class="fas fa-envelope me-3"></i>
                        info@example.com
                    </p>
                    <p><i class="fas fa-phone me-3"></i> + 01 234 567 88</p>
                    <p><i class="fas fa-print me-3"></i> + 01 234 567 89</p>
                </div>
                <!-- Grid column -->
            </div>
            <!-- Grid row -->
        </div>
    </section>
    <!-- Section: Links  -->

    <!-- Copyright -->
    <div class="text-center p-4" style="background-color: rgba(0, 0, 0, 0.05);">
        © 2021 Copyright:
        <a class="text-reset fw-bold" href="https://mdbootstrap.com/">MDBootstrap.com</a>
    </div>
    <!-- Copyright -->
</footer>
<!-- Footer -->

</body>
</html>