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
        Quản lý lịch trình tàu
    </title>
</head>
<body>

<div style="display: flex; height: 100vh">
    <aside style="width: 300px" class="menu py-5 px-1  has-shadow">

        <ul class="menu-list">
            <li><a>
                Trang chủ
            </a></li>
            <li ><a class="is-active">
                Quản lý ga
            </a></li>
            <li><a>
                Quản lý tàu
            </a></li>
            <li><a>
                Quản lý lịch trình
            </a></li>

        </ul>

    </aside>
    <main id="main"  class="p-5 has-background-white-ter" style="max-height: 100vh; flex: 1 1; overflow-y: auto">
        <jsp:doBody/>
    </main>

</div>

</body>
</html>
