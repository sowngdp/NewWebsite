<%--
  Created by IntelliJ IDEA.
  User: Laffy
  Date: 12/12/2023
  Time: 1:12 PM
  To change this template use File | Settings | File Templates.
--%>
<%@tag description="Main layout" pageEncoding="UTF-8" %>
<!DOCTYPE html>
<html lang="vi">
<head>
    <meta charset="UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1">
    <link rel="stylesheet" href="https://cdn.jsdelivr.net/npm/bulma@0.9.4/css/bulma.min.css">
    <title>
        Tin vịt
    </title>
</head>
<body>
<header id="pageheader">
    <nav class="navbar" role="navigation" aria-label="main navigation">
        <div class="navbar-brand">
            <a class="navbar-item" href="https://bulma.io">
                <img src="https://bulma.io/images/bulma-logo.png" width="112" height="28">
            </a>

            <a role="button" class="navbar-burger" aria-label="menu" aria-expanded="false"
               data-target="navbarBasicExample">
                <span aria-hidden="true"></span>
                <span aria-hidden="true"></span>
                <span aria-hidden="true"></span>
            </a>
        </div>

        <div id="navbarBasicExample" class="navbar-menu">
            <div class="navbar-start">
                <a class="navbar-item">
                    Trang chủ
                </a>


                <div class="navbar-item has-dropdown is-hoverable">
                    <a class="navbar-link">
                        Danh mục
                    </a>

                    <div class="navbar-dropdown">
                        <a class="navbar-item">
                            Thế giới
                        </a>
                        <a class="navbar-item">
                            Thể thao
                        </a>
                        <a class="navbar-item">
                            Giải trí
                        </a>

                    </div>
                </div>
            </div>

            <div class="navbar-end">
                <div class="navbar-item">
                    <div class="buttons">
                        <a class="button is-primary">
                            <strong>Sign up</strong>
                        </a>
                        <a class="button is-light">
                            Log in
                        </a>
                    </div>
                </div>
            </div>
        </div>
    </nav>
</header>

<main id="main" class="container" style="min-height: 80vh">
    <jsp:doBody/>
</main>

<footer id="pagefooter">
    <footer class="footer">
        <div class="content has-text-centered">
            <p>
                <strong>Bulma</strong> by <a href="https://jgthms.com">Jeremy Thomas</a>. The source code is licensed
                <a href="http://opensource.org/licenses/mit-license.php">MIT</a>. The website content
                is licensed <a href="http://creativecommons.org/licenses/by-nc-sa/4.0/">CC BY NC SA 4.0</a>.
            </p>
        </div>
    </footer>
</footer>
</body>
</html>
