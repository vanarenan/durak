<#import "/spring.ftl" as spring/>
<!DOCTYPE html>
<html lang="en">
<head>
    <meta charset="UTF-8">
    <link rel="stylesheet" href="/static/styles.css" type="text/css">
    <link rel="stylesheet" type="text/css" href="<@spring.url '/styles.css'/>"/>
    <title>Title</title>
    <style>
        h3 {
            font-family: 'Times New Roman', Times, serif; /* Гарнитура текста */
            font-size: 500%; /* Размер шрифта в процентах */
        }
    </style>
</head>
<body>
<h1><b>GAME TABLE</b></h1>
<h1><b>${count}</b></h1>
    <h1 align="center" >  ${message} </h1>
<img src="/img/fulldeck/back.png" alt="card">
<img src="${trump.img}" alt="card">

<a href="/ui/init">
    <button>New game</button>
</a>
<#--<a href="/ui/getcard"><button>Get card</button></a>-->
<a href="/ui/refill">
    <button>Refill cards</button>
</a>
<a href="/ui/computermove">
    <button>Computer's move</button>
</a>
<a href="/ui/trash">
    <button>Trash</button>
</a>
<a href="/ui/compturn">
    <button>Comp turn</button>
</a>


<#--<a href="/ui/compmove">
    <button>Comp move</button>
</a>
<a href="/ui/mymove">
    <button>My move</button>
</a>-->
<div style="border: solid 3px black">
    <h2>COMP</h2>
    <ul>
        <#list listComp as card>
            <li>
                <img src="${card.img}" alt="card">
            </li>
        </#list>
    </ul>
</div>
    <div>
        <div style="border: solid 3px black">
            <h2>Comp move</h2>
            <ul>
                <#list compMove as card>
                    <li>
                        <img src="${card.img}" alt="card">
                    </li>
                </#list>
            </ul>
        </div>
        <div style="border: solid 3px black">
            <h2>My move</h2>
            <ul>
                <#list myMove as card>
                    <li>
                        <img src="${card.img}" alt="card">
                    </li>
                </#list>
            </ul>
        </div>
    </div>
        <div style="border: solid 3px black">
            <h2>ME</h2>
            <ul>
                <#list list as card>
                    <li>
                        <a href="/ui/pick/${card.suit}/${card.nominal}">
                            <img src="${card.img}" alt="card">
                        </a>
                    </li>
                </#list>
            </ul>
        </div>
</body>
</html>