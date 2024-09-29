<!DOCTYPE html>
<html lang="en">
<head>
    <style>
        *{
    margin: 0;
    padding: 0;
    box-sizing: border-box ;
}

body{
    display: flex;
    justify-content: center;
    align-items: center;
    min-height: 100vh;
    background: url(k.jpg);
}

.wrapper{
    width: 750px;
    background: transparent;
    border: 2px solid rgba(225, 225, 225, 0.5);
    border-radius: 5px;
    padding: 55px 50px 70px;
    color: white;
}

.wrapper h1{
    font-size: 36px;
    text-align: center;
    margin-bottom: 20px;
    color: white;
    font-weight: bold;

}

.wrapper .input-box{
    display: flex;
    justify-content: space-between;
    flex-wrap: wrap;
}
.input-box .input-field{
    position: relative;
    width: 48%;
    height: 50px;
    margin: 13px 0;

}
.input-box .input-field input{
    width: 100%;
    height: 100%;
    background: transparent;
    font-size: 16px;
    color: white;
    border-radius: 6px;
    border: none;
    outline: none;
    border-bottom: 2px solid white;
}

.input-box .input-field input::placeholder{
   color: white;
}

.input-box .input-field ion-icon{
    position: absolute;
    top: 50%;
    right: 8%;
    transform: translateY(-50%);
    font-size: 20px;
}
.wrapper label{
    display: inline-block;
    font-size: 14.5px;
    margin: 10px 0 23px;
    color: white;
    font-weight: bold;
    gap: 0.7;
}
.wrapper label input{
    accent-color: #fff;
    margin-right: 5px;
}
button{
    width: 100%;
    height: 48px;
    background-color: #f9004d;
    color: white;
    text-decoration: none;
    border: 2px solid transparent;
    font-weight: bold;
    font-size: 15px;
    padding: 13px 30px;
    border-radius: 30px;
    transition: .5s;
}
button:hover{
    background-color: transparent;
    border: 2px solid #f9004d;
    cursor: pointer;
}
span{
    color: #f9004d;
}

.sign-in{
    font-size: .9em;
    color: #fff;
    text-align: center;
    margin: 20px 5px;
    font-weight: bold;
}
.sign-in a{
    color: #f9004d;
    text-decoration: none;
    font-weight: bold;
}
.form-control{
    border: 2px solid white;
    border: none;
    border-bottom: 2px solid white;
    display: block;
    width: 100%;
    height: 50px;
    padding-right: 0;
    margin-bottom: 25px;
    background: transparent;
    outline: none;
    border-radius: 10px;
}
select{
    color: white;
    font-size: 16px;
}
option{
    color: black;
}

    </style>
    <!-- <link rel="stylesheet" href="form.css" /> -->
    <title>Registration Page</title>
</head>
<body>
   <div class="wrapper">
    <form action="/CreateAccount/demo" method="post">
        <h1>Register <Span>Now</Span></h1>

        <div class="input-box">

            <div class="input-field">
                
                <input type="text" placeholder="User name" id="username" name="username" required>
                <ion-icon name="people-outline"></ion-icon> 
            </div>
            <div class="input-field">
                
                <input type="password" placeholder="Password"  maxlength="10" id="password" name="password" required>
                <ion-icon name="lock-closed-outline"></ion-icon> 
            </div>
        </div>
        <div class="input-box">
            <div class="input-field">
                
                <input type="text" placeholder="Number" id="mobile" name="mobile" required> 
                <ion-icon name="call-outline"></ion-icon>
            </div>

            <div class="input-field">
                
                <input type="date" placeholder="DOB" id="dob" name="dob" required>
            </div>
        </div>
          
        <div class="input-box">
            <div class="input-field">
                
                <input type="text" placeholder="Address" id="address"name="address" required>
                <ion-icon name="home-outline"></ion-icon> 
            </div>

            <div class="input-field">
                
                <input type="text" placeholder="Qualification" id="qualification" name="qualification" required>
                <ion-icon name="reader-outline"></ion-icon> 
            </div>
        </div>
        
           

        <div class="input-box">
            <div class="input-field">
                
                <input type="email" placeholder="Enter your email" id="email" name="email" required>
                <ion-icon name="mail-outline"></ion-icon> 
            </div>

            <div class="input-field">
                
                <select name="gender" id="gender" class="form-control">
                    <option value="" disabled selected>Gender</option>
                    <option value="male">Male</option>
                    <option value="female">Female</option>
                    <option value="other">Other</option>
                </select>
                <ion-icon name="man-outline"></ion-icon>
            </div>
        </div>
        <label><input type="checkbox">Remember Me</label>
        <button class="submite">Register</button>
        <div class="sign-in">
            <p>Already have an account, <a href="login.jsp">Click here to log in.</a></p><br>
            <p>Click here to go to the <a href="index.jsp">Home Page.</a></p>
        </div>
    </form>
   </div>
   <script type="module" src="https://unpkg.com/ionicons@7.1.0/dist/ionicons/ionicons.esm.js"></script>
<script nomodule src="https://unpkg.com/ionicons@7.1.0/dist/ionicons/ionicons.js"></script>
</body>
</html>