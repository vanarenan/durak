<#import "/spring.ftl" as spring/>
<!DOCTYPE html>
<html lang="en">
<head>
    <meta charset="UTF-8">
    <link rel="stylesheet" href="/static/style.css" type="text/css">
    <link rel="stylesheet" type="text/css" href="<@spring.url '/style.css'/>"/>
    <title>Title</title>
</head>
<body>
<h1><b>DESK LIST</b></h1>
<h1><b>${count}</b></h1>
<a href="/ui/init"><button>New game</button></a>
<a href="/ui/shuffle"><button>Shuffle</button></a>
<#--<a href="/ui/getcard"><button>Get card</button></a>-->
<a href="/ui/refill"><button>Refill</button></a>
<ul class="allCard">
    <#list list as card>
        <li><img src="${card.img}" alt="card"></li>
    </#list>
</ul>
</body>
</html>