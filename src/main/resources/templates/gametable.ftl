<#import "/spring.ftl" as spring/>
<!DOCTYPE html>
<html lang="en">
<head>
    <meta charset="UTF-8">
    <link rel="stylesheet" type="text/css" href="<@spring.url '/styles.css'/>"/>
    <title>Durak</title>
</head>
<body>
<h1 align="center">
    <a href="/ui/init"><button>New game</button></a>
    <a href="/ui/trash"><button>Trash</button></a>
    <p><#if message>Player</#if><#if !message>Comp</#if> turn</p>
</h1>
<div class="container">
    <div class="deck-container">
        <p>${count}</p>
        <#if (count>1)><img src="/img/fulldeck/back.png" alt="card"></#if>
        <#if (count>0)><img src="${trump.img}" alt="card"></#if>
    </div>
    <div class="cards-container">
        <div>
            <ul>
                <#list listComp as card>
                    <li>
                        <img src="${card.img}" alt="card"> ${card.value}
                    </li>
                </#list>
            </ul>
        </div>
        <div>
            <ul>
                <#list compMove as card>
                    <li>
                        <img src="${card.img}" alt="card">
                    </li>
                </#list>
            </ul>
        </div>
        <div>
            <ul>
                <#list myMove as card>
                    <li>
                        <img src="${card.img}" alt="card">
                    </li>
                </#list>
            </ul>
        </div>
        <div>
            <ul>
                <#list list as card>
                    <li>
                        <a href="/ui/pick/${card.suit}/${card.nominal}">
                            <img src="${card.img}" alt="card">
                        </a>
                        ${card.value}
                    </li>
                </#list>
            </ul>
        </div>
    </div>
</div>
</body>
</html>