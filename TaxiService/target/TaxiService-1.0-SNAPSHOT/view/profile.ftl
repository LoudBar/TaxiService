<!DOCTYPE html>
<html lang="en">
<head>
    <meta charset="UTF-8">
    <link rel="stylesheet" type="text/css" href="../resources/css/style.css">

    <div class="card">
        <#if user.avatarId??>
            <img class="avatar" alt="IMAGE" src="/files/${user.avatarId}" />
        <#else>
            <img class="avatar" alt="IMAGE" src="../img/no-avatar.png" />
        </#if>
        <h1 class="title"> ${user.firstName} </h1>

        <form class="upload" action="/file-upload">
            <button class="upload-button">Upload avatar</button>
        </form>

        <form class="upload" action="/takeTrip">
            <button class="upload-button">Order</button>
        </form>

        <form class="upload" action="/shifts">
            <button class="upload-button">Manage trips</button>
        </form>
    </div>

</head>
</html>