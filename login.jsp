<!DOCTYPE html>
<html lang="en">
<head>
    <meta charset="UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <link rel="stylesheet" href="login.css" />
    <title>Login Page</title>
</head>
<body>
    <section>
        <div class="form-box">
            <div class="form-value">
                <form action="LoginServlet" method="post">
                    <h2>Log<span>in</span></h2>
                    <div class="inputbox">
                        <ion-icon name="mail-outline"></ion-icon>
                        <input type="email" placeholder="Email" id="email" name="email" required>
                        <!-- <label for="">Email</label> -->
                    </div>

                    <div class="inputbox">
                        <ion-icon name="lock-closed-outline"></ion-icon>
                        <input type="password" placeholder="Password" id="password" name="password" maxlength="10" required>
                        <!-- <label for="">  Password</label> -->
                    </div>
                    <div class="forget">
                        <label for=""><input type="checkbox">Remember Me, </label>
                        <a href="#">Forget Password</a>
                    </div>

                    <button>Log in</button>
                    <div class="sign-in">
                        <p>Don't have a account, <a href="form.jsp">Sign In</a></p>
                        <p>Don't have a account, <a href="index.jsp">Go to the home page</a></p>
                    </div>
                </form>
            </div>
        </div>
    </section>

    <script type="module" src="https://unpkg.com/ionicons@7.1.0/dist/ionicons/ionicons.esm.js"></script>
<script nomodule src="https://unpkg.com/ionicons@7.1.0/dist/ionicons/ionicons.js"></script>

</body>
</html>