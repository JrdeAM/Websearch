# Implementação dos padrões Strategy e Observer - WebSearch

## Objetivo do Projeto

O projeto consiste na implementação de um sistema de busca utilizando o padrão de projeto **Strategy**.

O objetivo inicial era permitir que o modelo de busca realizasse a leitura de consultas armazenadas em um arquivo e notificasse objetos interessados nessas informações.

Durante a implementação, o sistema foi evoluído para permitir que cada observador possua uma regra própria de filtragem, recebendo somente consultas que atendam aos seus critérios.

A implementação adicionou:

- uma interface para filtros de consulta;
- diferentes estratégias de filtragem;
- associação entre observadores e filtros;
- verificação de interesse antes da notificação.

---

# Adicionar interface para filtros de consulta usando Strategy

Nesta primeira etapa foi criada a estrutura inicial para utilização do padrão **Strategy**.

O problema inicial era que o modelo de busca não possuía uma forma flexível de definir quais consultas deveriam ser enviadas aos observadores.

Para resolver esse problema foi criada a interface:

- `QueryFilter.java`

A interface define o comportamento que todos os filtros de consulta devem possuir.

Foi criado o método:

```java
boolean filter(String query);
```

Esse método recebe uma consulta em formato de texto e retorna:

- `true` → quando o observador possui interesse na consulta;
- `false` → quando a consulta deve ser ignorada.

---

A criação da interface permitiu separar a lógica de filtragem do modelo principal de busca.

A estrutura passou a funcionar da seguinte forma:

```
QueryFilter

      |

      |

Implementações de filtros
```

Dessa forma, novos filtros podem ser adicionados sem alterar o funcionamento do modelo de busca.

---

# Implementar estratégias de filtro para consultas

Nesta etapa foram criadas implementações concretas da interface `QueryFilter`.

Foram adicionados diferentes tipos de filtros para demonstrar a utilização do padrão Strategy.

---

## KeywordQueryFilter

Responsável por verificar se uma consulta possui determinada palavra.

Exemplo:

```java
KeywordQueryFilter("king")
```

Consulta:

```
The king arrived
```

Resultado:

```
true
```

Consulta:

```
The queen arrived
```

Resultado:

```
false
```

Esse filtro permite que um observador receba somente consultas relacionadas a uma palavra específica.

---

## AllQueryFilter

Responsável por aceitar todas as consultas recebidas.

Funcionamento:

```
Qualquer consulta

        ↓

Retorna true

        ↓

Observador recebe a informação
```

Esse filtro mantém o comportamento original do sistema, permitindo que todas as consultas sejam processadas.

---

Com essas implementações, o sistema passou a permitir diferentes estratégias de filtragem utilizando a mesma interface.

---

# Alterar registro de observadores para aceitar filtros de consulta individuais

Nesta etapa o relacionamento entre observadores e filtros foi alterado.

Inicialmente, o filtro era tratado como uma regra geral do modelo de busca.

Porém, o comportamento esperado era permitir que cada observador tivesse um interesse diferente.

Exemplo:

```
Observador 1 → interessado em "king"

Observador 2 → interessado em "queen"
```

---

Para realizar essa alteração foi criada uma associação entre:

- observador;
- filtro responsável pela consulta.

O registro passou a aceitar:

```java
addQueryObserver(observer, filter);
```

Agora cada observador possui seu próprio filtro.

---

A estrutura passou a funcionar da seguinte maneira:

```
              WebSearchModel

                    |

        ----------------------------

        |                          |

 Observador 1              Observador 2

 Filtro "king"             Filtro "queen"
```

Isso permite que diferentes usuários recebam diferentes resultados a partir da mesma fonte de dados.

---

# Aplicar filtros individuais dos observadores antes de enviar consultas

Nesta etapa foi implementada a verificação dos filtros antes da notificação dos observadores.

Antes da alteração, o fluxo era:

```
Consulta encontrada

        ↓

Notifica todos os observadores
```

Esse comportamento fazia com que todos recebessem consultas mesmo quando não possuíam interesse.

---

Após a alteração, o fluxo passou a ser:

```
Consulta encontrada

        ↓

Verifica filtro do observador

        ↓

O filtro aceita?

        ↓

Sim → Notifica observador

Não → Ignora consulta
```

---

O modelo de busca passou a executar a regra de filtragem antes de chamar o método de atualização do observador.

Exemplo:

Consulta:

```
My friend is here
```

Filtro:

```java
KeywordQueryFilter("friend")
```

Resultado:

```
true
```

O observador recebe a consulta.

---

Consulta:

```
The castle is old
```

Resultado:

```
false
```

A consulta não é enviada.

---

Essa alteração garantiu que cada observador recebesse somente informações relevantes para seu interesse.

---

# Adicionar observadores com filtros personalizados para consultas

Nesta etapa o cliente `Snooper.java` foi alterado para criar diferentes observadores utilizando filtros específicos.

Foram adicionados dois observadores independentes.

---

## Primeiro observador

Responsável por receber consultas que contenham a palavra:

```
friend
```

O filtro utilizado não diferencia letras maiúsculas e minúsculas.

Exemplo:

Consulta:

```
My FRIEND is here
```

Resultado:

```
Oh Yes! My FRIEND is here
```

---

## Segundo observador

Responsável por receber consultas que possuem mais de 60 caracteres.

Exemplo:

Consulta longa:

```
Uma consulta contendo mais de sessenta caracteres
```

Resultado:

```
So long Uma consulta contendo mais de sessenta caracteres
```

---

A implementação demonstrou que diferentes observadores podem possuir regras diferentes de interesse utilizando a mesma estrutura.

---

# Resultado Final

Após todas as etapas, o projeto WebSearch passou a utilizar o padrão **Strategy**.

A implementação final permite:

- criar novos filtros sem alterar o modelo de busca;
- associar diferentes filtros para diferentes observadores;
- verificar o interesse do observador antes da notificação;
- adicionar novos comportamentos de consulta de forma independente.

O fluxo final do sistema ficou:

```
Arquivo de consultas

        ↓

WebSearchModel

        ↓

Verifica filtro do observador

        ↓

Consulta aceita?

        ↓

Notifica observador
```

A aplicação dos padrões tornou o projeto mais organizado, flexível e preparado para futuras extensões.