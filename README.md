# Java A* Pathfinding
#### Programinha básico para terminal que trabalha o Algorítimo de Busca A-Estrela (A*) implementado na Linguagem Java.
v 1.0<br />
*Data de lançamento da versão:* 17/07/2020

<hr />

Para testar este sistema, primeiro você deve baixar este repositório ou cloná-lo através do seu Git.

Feito isso, teremos um arquivo _.jar_ dentro da pasta **/dist/**.
Abra o console dentro desta pasta e digite:

```
java -jar aStarPathfinding.jar
```

E o programa rodará em seu terminal.
Escolha a posição do ponto de origem, sendo as colunas de A até E e as linhas de 0 até 4.


>### Exemplo:
>Para acessar a coluna B na linha 2, digite B2.

Em seguida fará o mesmo com o ponto destino.
Se a posição escolhida estiver em um bloco, será lançada uma exceção, impedindo de você marcar um local onde não é possível passar.

Após a escolha das duas coordenadas, o trajeto será montado, onde o sistema deve encontrar o caminho mais curto possível através de cálculos matemáticos e então é mostrado o mapa com o trajeto montado.
