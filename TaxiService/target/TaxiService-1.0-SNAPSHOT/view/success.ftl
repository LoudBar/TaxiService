<!DOCTYPE html>
<html lang="en">
<#setting date_format="dd MMMM yyyy 'г.,' HH:mm">
<head>
    <meta charset="UTF-8">
    <link rel="stylesheet" type="text/css" href="../resources/css/style.css">

    <div class="card">

        <div class="departure">
            <p>Departure: ${shift.departurePlace}</p>
            <p>Arrival: ${shift.arrivalPlace}</p>
            <p>Date: ${shift.date?datetime}</p>
            <div class="driver-avatar">
                ${shift.driver.firstName} <img class="driver-avatar" alt="IMAGE" src="/files/${shift.driver.avatarId}"/>
            </div>
        </div>

        <form class="back-to-profile" action="/profile">
            <button class="upload-button">Upload avatar</button>
        </form>

        <form class="shifts" action="/shifts">
            <button class="upload-button">Manage trips</button>
        </form>
    </div>

</head>
</html>