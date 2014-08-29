# Demoiselle Scheduler

## Configuração do Projeto

    O componente funciona em métodos das
    camadas de Negócio e de Persistência, ou seja,
    em métodos de classes
    anotadas com *@BusinessController e @PersistenceControler*.

### O primeiro passo é importar o componente no pom.xml

```xml
<dependency>
    <groupId>br.gov.frameworkdemoiselle.component</groupId>
    <artifactId>demoiselle-scheduler-quartz</artifactId>
    <version>1.0.0-SNAPSHOT</version>
</dependency>
```

### Depois é só anotar o método que você deseja que seja executado com @Schedule.


```java
@Schedule(cron = "0 0/1 * * * ?")
public void qualquerMetodo() {
    ...Exemplo de execução minuto a minuto...
}
```


### O parâmetro cron:

O parâmetro cron segue o padrão unix, basicamente são 7 campos separados por espaço onde cada campo representa uma
         variável de tempo vejamos a tabela:

Nome|Obrigatório|Valores|Símbolos
----|-----------|-------|---------------
Segundos|SIM|0-59|, - * /
Minutos|SIM|0-59 |, - * /
Horas|SIM|0-23 |, - * /
Dia do Mês|SIM|1-31|, - * ? / L W
Mês|SIM|1-12 or JAN-DEC|, - * /
Dia da Semana|SIM |1-7 or SUN-SAT |, - * ? / L #
Ano|NÃO |vazio, 1970-2099|, - * /

Os campos podem conter símbolos e cada um tem sua representatividade:

(*) ("Todos os valores") - Usado para selecionar todos os valores dentro de um campo.
Por exemplo, * no campo minuto significa "a cada minuto".

(?) ("nenhum valor específico") - útil quando você precisa especificar algo em um dos dois
campos, mas não o outro. Por exemplo, se eu quero a execução de um método em um determinado
dia do mês (por exemplo, dia 10), mas não me importo que o dia da semana que passa a ser,
você coloca "10" no campo dia do mês, e "?" no campo dia da semana.
Veja os exemplos abaixo para esclarecimentos.

(-) Usado para especificar intervalos. Por exemplo, "10-12" no campo hora significa
que será executado no intervalo, ou seja as horas de 10, 11 e 12 horas.

(,) Usado para especificar valores adicionais. Por exemplo, "MON,WED,FRI"
no campo dia da semana significa "os dias de segunda-feira, quarta-feira, e sexta-feira".

(/) Usado para especificar incrementos. Por exemplo, "0/15" no campo dos segundos significa
que irá executar nos segundos 0, 15, 30, e 45". E "5/15" significa que o primeiro é quando inicia
e o segundo o intervalo de repetição, então será assim " 5, 20, 35 e 50".
"1/3" no campo dia do mês significa "executar a cada 3 dias, com início no primeiro dia do mês".

(L) ("last") - Tem um significado diferente em cada um dos dois campos em que é permitido.
Por exemplo, o valor "L" no campo dia do mês significa "o último dia do mês" - dia 31 de janeiro,
dia 28 de fevereiro em anos não-bissextos. Se usado no campo Dia-de-semana, por si só, significa
simplesmente "7" ou "SAT". Mas, se usado no campo Dia da semana depois de um outro valor,
que significa "o último dia do mês xxx" - por exemplo, "6L" significa "a última sexta-feira do mês".
Ao usar a opção 'L', é importante não especificar listas ou faixas de valores, porque você
vai obter resultados confusos.

(W) ("dia da semana") Usado para especificar os dias uteis (segunda a sexta) mais próximo o dia dado.
Como exemplo, se você fosse para especificar "15W" como o valor para o campo dia do mês,
o significado é: "o dia da semana mais próximo ao dia 15 do mês". Portanto, se o 15 é um sábado,
o método será acionado na sexta-feira dia 14. Se o dia 15 é um domingo, e o próximo agendamento será
acionado na segunda-feira dia 16. Se o dia 15 é uma terça-feira, em seguida, ele dispara na terça-feira
dia 15. No entanto, se você especificar "1W" como o valor para o dia do mês, e o primeiro é um sábado,
o método será acionado na segunda-feira dia 3, uma vez que não vai "saltar" sobre o limite de dias
de um mês. O caráter 'W' só pode ser especificado quando o dia do mês é um único dia, não um intervalo
ou lista de dias.

O 'L' e os 'W' também pode ser combinado no campo dia do mês para produzir "LW", que se traduz em
"último dia útil do mês".

(#) Usado para especificar a "enésima" XXX dia do mês. Por exemplo, o valor de "6 # 3" no campo dia da
semana significa "a terceira sexta-feira do mês" (dia 6 = sexta-feira e "# 3" = a 3 em um mês).
Outros exemplos: "2 # 1" = a primeira segunda-feira do mês e "4 # 5" = o quinto quarta-feira do mês.
Note que se você especificar "# 5" e não há quinta quarta-feita do mês determinado no mês, então nenhum
disparo ocorrerá isso.

Os parêmetros e os nomes dos meses e dias da semana não são case sensitive. MON é o mesmo que mon.

Exemplos|Descrição
--------|------------------------------
0 0 * 12 *?|Executa às 12h (meio-dia) todos os dias
0 15 10? * |Executa às 10:15 todos os dias
0 * 15 10 *?|Executa às 10:15 todos os dias
0 * 15 10 *? *|Executa às 10:15 todos os dias
0 * 15 10 *? 2005|Executa às 10:15 todos os dias durante o ano de 2005
0 * 14 *? |Executa a cada minuto a partir de 14:00 e terminando às 02:59, todos os dias
0 0/5 * 14 *? |Executa a cada cinco minutos a partir de 14:00 e terminando às 02:55, todos os dias
0 0/5 * 14,18 *? |Executa a cada cinco minutos a partir de 14:00 e terminando às 02:55, e fogo a cada 5 minutos a partir de 06:00 e terminando às 06:55, todos os dias
0 0-5 14 *? |Executa a cada minuto a partir de 14:00 e terminando às 14:05, todos os dias
0 10,44 14? 3 WED|Executa at 14:10 e às 02:44 toda quarta-feira no mês de março.
0 15 10? * |Executa de segunda a sexta às 10h15 todas as segundas, terças, quartas, quintas e sextas-feiras
0 15 10 15 *? |Executa às 10:15 horas do dia 15 de cada mês
0 15 10 L *? |Executa às 10:15 no último dia de cada mês
0 15 10? * 6L |Executa at 10:15 na última sexta-feira de cada mês
0 15 10? * 6L |Executa at 10:15 na última sexta-feira de cada mês
0 15 10? * 6L  2002-2005 |Executa at 10:15 em cada última sexta-feira de cada mês durante os anos de 2002, 2003, 2004 e 2005
0 15 10? * 6 # 3 |Executa às 10:15 na terceira sexta-feira de cada mês
0 0 12 1/5 *? |Executa às 12h (meio-dia) a cada 5 dias a cada mês, começando no primeiro dia do mês.
0 11 11 11 11? |Executa a cada 11 de novembro às 11:11 horas.

Seguem os links para maiores detalhes:

    http://pt.wikipedia.org/wiki/Cron

    http://en.wikipedia.org/wiki/Cron

### Cluster



Para aplicações que utilizem cluster é só seguir essas recomendações:


Coloque um arquivo quartz.porperties em resources da sua app
Exemplo de properties com MySQL:

```java
#============================================================================
# Configure Main Scheduler Properties
#============================================================================

org.quartz.scheduler.skipUpdateCheck = false
org.quartz.scheduler.instanceName = DatabaseClusteredScheduler
org.quartz.scheduler.instanceId = AUTO
org.quartz.scheduler.jobFactory.class = org.quartz.simpl.SimpleJobFactory

#============================================================================
# Configure ThreadPool
#============================================================================

org.quartz.threadPool.class = org.quartz.simpl.SimpleThreadPool
org.quartz.threadPool.threadCount = 25
org.quartz.threadPool.threadPriority = 5

#============================================================================
# Configure JobStore
#============================================================================

# Sem Cluster
#org.quartz.jobStore.class = org.quartz.simpl.RAMJobStore

# Com Cluster
org.quartz.jobStore.class = org.quartz.impl.jdbcjobstore.JobStoreTX

org.quartz.jobStore.driverDelegateClass = org.quartz.impl.jdbcjobstore.StdJDBCDelegate
org.quartz.jobStore.dataSource = quartzDataSource
org.quartz.jobStore.tablePrefix = QRTZ_
org.quartz.jobStore.isClustered = true
org.quartz.jobStore.misfireThreshold = 60000
org.quartz.jobStore.useProperties = false
org.quartz.jobStore.clusterCheckinInterval = 20000

#============================================================================
# Configure Datasources
#============================================================================

org.quartz.dataSource.quartzDataSource.driver = com.mysql.jdbc.Driver
org.quartz.dataSource.quartzDataSource.URL = jdbc:mysql://10.10.10.10:3306/quartz
org.quartz.dataSource.quartzDataSource.user = root
org.quartz.dataSource.quartzDataSource.password = root
org.quartz.dataSource.quartzDataSource.maxConnections = 1
```

Banco de dados

Depois crie a base de dados para o quartz utilizar (MySql)

```sql
DROP TABLE IF EXISTS QRTZ_FIRED_TRIGGERS;
DROP TABLE IF EXISTS QRTZ_PAUSED_TRIGGER_GRPS;
DROP TABLE IF EXISTS QRTZ_SCHEDULER_STATE;
DROP TABLE IF EXISTS QRTZ_LOCKS;
DROP TABLE IF EXISTS QRTZ_SIMPLE_TRIGGERS;
DROP TABLE IF EXISTS QRTZ_SIMPROP_TRIGGERS;
DROP TABLE IF EXISTS QRTZ_CRON_TRIGGERS;
DROP TABLE IF EXISTS QRTZ_BLOB_TRIGGERS;
DROP TABLE IF EXISTS QRTZ_TRIGGERS;
DROP TABLE IF EXISTS QRTZ_JOB_DETAILS;
DROP TABLE IF EXISTS QRTZ_CALENDARS;


CREATE TABLE QRTZ_JOB_DETAILS(
SCHED_NAME VARCHAR(120) NOT NULL,
JOB_NAME VARCHAR(200) NOT NULL,
JOB_GROUP VARCHAR(200) NOT NULL,
DESCRIPTION VARCHAR(250) NULL,
JOB_CLASS_NAME VARCHAR(250) NOT NULL,
IS_DURABLE VARCHAR(1) NOT NULL,
IS_NONCONCURRENT VARCHAR(1) NOT NULL,
IS_UPDATE_DATA VARCHAR(1) NOT NULL,
REQUESTS_RECOVERY VARCHAR(1) NOT NULL,
JOB_DATA BLOB NULL,
PRIMARY KEY (SCHED_NAME,JOB_NAME,JOB_GROUP))
TYPE=InnoDB;


CREATE TABLE QRTZ_TRIGGERS (
SCHED_NAME VARCHAR(120) NOT NULL,
TRIGGER_NAME VARCHAR(200) NOT NULL,
TRIGGER_GROUP VARCHAR(200) NOT NULL,
JOB_NAME VARCHAR(200) NOT NULL,
JOB_GROUP VARCHAR(200) NOT NULL,
DESCRIPTION VARCHAR(250) NULL,
NEXT_FIRE_TIME BIGINT(13) NULL,
PREV_FIRE_TIME BIGINT(13) NULL,
PRIORITY INTEGER NULL,
TRIGGER_STATE VARCHAR(16) NOT NULL,
TRIGGER_TYPE VARCHAR(8) NOT NULL,
START_TIME BIGINT(13) NOT NULL,
END_TIME BIGINT(13) NULL,
CALENDAR_NAME VARCHAR(200) NULL,
MISFIRE_INSTR SMALLINT(2) NULL,
JOB_DATA BLOB NULL,
PRIMARY KEY (SCHED_NAME,TRIGGER_NAME,TRIGGER_GROUP),
FOREIGN KEY (SCHED_NAME,JOB_NAME,JOB_GROUP)
REFERENCES QRTZ_JOB_DETAILS(SCHED_NAME,JOB_NAME,JOB_GROUP))
TYPE=InnoDB;


CREATE TABLE QRTZ_SIMPLE_TRIGGERS (
SCHED_NAME VARCHAR(120) NOT NULL,
TRIGGER_NAME VARCHAR(200) NOT NULL,
TRIGGER_GROUP VARCHAR(200) NOT NULL,
REPEAT_COUNT BIGINT(7) NOT NULL,
REPEAT_INTERVAL BIGINT(12) NOT NULL,
TIMES_TRIGGERED BIGINT(10) NOT NULL,
PRIMARY KEY (SCHED_NAME,TRIGGER_NAME,TRIGGER_GROUP),
FOREIGN KEY (SCHED_NAME,TRIGGER_NAME,TRIGGER_GROUP)
REFERENCES QRTZ_TRIGGERS(SCHED_NAME,TRIGGER_NAME,TRIGGER_GROUP))
TYPE=InnoDB;


CREATE TABLE QRTZ_CRON_TRIGGERS (
SCHED_NAME VARCHAR(120) NOT NULL,
TRIGGER_NAME VARCHAR(200) NOT NULL,
TRIGGER_GROUP VARCHAR(200) NOT NULL,
CRON_EXPRESSION VARCHAR(120) NOT NULL,
TIME_ZONE_ID VARCHAR(80),
PRIMARY KEY (SCHED_NAME,TRIGGER_NAME,TRIGGER_GROUP),
FOREIGN KEY (SCHED_NAME,TRIGGER_NAME,TRIGGER_GROUP)
REFERENCES QRTZ_TRIGGERS(SCHED_NAME,TRIGGER_NAME,TRIGGER_GROUP))
TYPE=InnoDB;


CREATE TABLE QRTZ_SIMPROP_TRIGGERS
(
SCHED_NAME VARCHAR(120) NOT NULL,
TRIGGER_NAME VARCHAR(200) NOT NULL,
TRIGGER_GROUP VARCHAR(200) NOT NULL,
STR_PROP_1 VARCHAR(512) NULL,
STR_PROP_2 VARCHAR(512) NULL,
STR_PROP_3 VARCHAR(512) NULL,
INT_PROP_1 INT NULL,
INT_PROP_2 INT NULL,
LONG_PROP_1 BIGINT NULL,
LONG_PROP_2 BIGINT NULL,
DEC_PROP_1 NUMERIC(13,4) NULL,
DEC_PROP_2 NUMERIC(13,4) NULL,
BOOL_PROP_1 VARCHAR(1) NULL,
BOOL_PROP_2 VARCHAR(1) NULL,
PRIMARY KEY (SCHED_NAME,TRIGGER_NAME,TRIGGER_GROUP),
FOREIGN KEY (SCHED_NAME,TRIGGER_NAME,TRIGGER_GROUP)
REFERENCES QRTZ_TRIGGERS(SCHED_NAME,TRIGGER_NAME,TRIGGER_GROUP))
TYPE=InnoDB;


CREATE TABLE QRTZ_BLOB_TRIGGERS (
SCHED_NAME VARCHAR(120) NOT NULL,
TRIGGER_NAME VARCHAR(200) NOT NULL,
TRIGGER_GROUP VARCHAR(200) NOT NULL,
BLOB_DATA BLOB NULL,
PRIMARY KEY (SCHED_NAME,TRIGGER_NAME,TRIGGER_GROUP),
INDEX (SCHED_NAME,TRIGGER_NAME, TRIGGER_GROUP),
FOREIGN KEY (SCHED_NAME,TRIGGER_NAME,TRIGGER_GROUP)
REFERENCES QRTZ_TRIGGERS(SCHED_NAME,TRIGGER_NAME,TRIGGER_GROUP))
TYPE=InnoDB;


CREATE TABLE QRTZ_CALENDARS (
SCHED_NAME VARCHAR(120) NOT NULL,
CALENDAR_NAME VARCHAR(200) NOT NULL,
CALENDAR BLOB NOT NULL,
PRIMARY KEY (SCHED_NAME,CALENDAR_NAME))
TYPE=InnoDB;


CREATE TABLE QRTZ_PAUSED_TRIGGER_GRPS (
SCHED_NAME VARCHAR(120) NOT NULL,
TRIGGER_GROUP VARCHAR(200) NOT NULL,
PRIMARY KEY (SCHED_NAME,TRIGGER_GROUP))
TYPE=InnoDB;


CREATE TABLE QRTZ_FIRED_TRIGGERS (
SCHED_NAME VARCHAR(120) NOT NULL,
ENTRY_ID VARCHAR(95) NOT NULL,
TRIGGER_NAME VARCHAR(200) NOT NULL,
TRIGGER_GROUP VARCHAR(200) NOT NULL,
INSTANCE_NAME VARCHAR(200) NOT NULL,
FIRED_TIME BIGINT(13) NOT NULL,
SCHED_TIME BIGINT(13) NOT NULL,
PRIORITY INTEGER NOT NULL,
STATE VARCHAR(16) NOT NULL,
JOB_NAME VARCHAR(200) NULL,
JOB_GROUP VARCHAR(200) NULL,
IS_NONCONCURRENT VARCHAR(1) NULL,
REQUESTS_RECOVERY VARCHAR(1) NULL,
PRIMARY KEY (SCHED_NAME,ENTRY_ID))
TYPE=InnoDB;


CREATE TABLE QRTZ_SCHEDULER_STATE (
SCHED_NAME VARCHAR(120) NOT NULL,
INSTANCE_NAME VARCHAR(200) NOT NULL,
LAST_CHECKIN_TIME BIGINT(13) NOT NULL,
CHECKIN_INTERVAL BIGINT(13) NOT NULL,
PRIMARY KEY (SCHED_NAME,INSTANCE_NAME))
TYPE=InnoDB;


CREATE TABLE QRTZ_LOCKS (
SCHED_NAME VARCHAR(120) NOT NULL,
LOCK_NAME VARCHAR(40) NOT NULL,
PRIMARY KEY (SCHED_NAME,LOCK_NAME))
TYPE=InnoDB;


CREATE INDEX IDX_QRTZ_J_REQ_RECOVERY ON QRTZ_JOB_DETAILS(SCHED_NAME,REQUESTS_RECOVERY);
CREATE INDEX IDX_QRTZ_J_GRP ON QRTZ_JOB_DETAILS(SCHED_NAME,JOB_GROUP);


CREATE INDEX IDX_QRTZ_T_J ON QRTZ_TRIGGERS(SCHED_NAME,JOB_NAME,JOB_GROUP);
CREATE INDEX IDX_QRTZ_T_JG ON QRTZ_TRIGGERS(SCHED_NAME,JOB_GROUP);
CREATE INDEX IDX_QRTZ_T_C ON QRTZ_TRIGGERS(SCHED_NAME,CALENDAR_NAME);
CREATE INDEX IDX_QRTZ_T_G ON QRTZ_TRIGGERS(SCHED_NAME,TRIGGER_GROUP);
CREATE INDEX IDX_QRTZ_T_STATE ON QRTZ_TRIGGERS(SCHED_NAME,TRIGGER_STATE);
CREATE INDEX IDX_QRTZ_T_N_STATE ON QRTZ_TRIGGERS(SCHED_NAME,TRIGGER_NAME,TRIGGER_GROUP,TRIGGER_STATE);
CREATE INDEX IDX_QRTZ_T_N_G_STATE ON QRTZ_TRIGGERS(SCHED_NAME,TRIGGER_GROUP,TRIGGER_STATE);
CREATE INDEX IDX_QRTZ_T_NEXT_FIRE_TIME ON QRTZ_TRIGGERS(SCHED_NAME,NEXT_FIRE_TIME);
CREATE INDEX IDX_QRTZ_T_NFT_ST ON QRTZ_TRIGGERS(SCHED_NAME,TRIGGER_STATE,NEXT_FIRE_TIME);
CREATE INDEX IDX_QRTZ_T_NFT_MISFIRE ON QRTZ_TRIGGERS(SCHED_NAME,MISFIRE_INSTR,NEXT_FIRE_TIME);
CREATE INDEX IDX_QRTZ_T_NFT_ST_MISFIRE ON QRTZ_TRIGGERS(SCHED_NAME,MISFIRE_INSTR,NEXT_FIRE_TIME,TRIGGER_STATE);
CREATE INDEX IDX_QRTZ_T_NFT_ST_MISFIRE_GRP ON QRTZ_TRIGGERS(SCHED_NAME,MISFIRE_INSTR,NEXT_FIRE_TIME,TRIGGER_GROUP,TRIGGER_STATE);


CREATE INDEX IDX_QRTZ_FT_TRIG_INST_NAME ON QRTZ_FIRED_TRIGGERS(SCHED_NAME,INSTANCE_NAME);
CREATE INDEX IDX_QRTZ_FT_INST_JOB_REQ_RCVRY ON QRTZ_FIRED_TRIGGERS(SCHED_NAME,INSTANCE_NAME,REQUESTS_RECOVERY);
CREATE INDEX IDX_QRTZ_FT_J_G ON QRTZ_FIRED_TRIGGERS(SCHED_NAME,JOB_NAME,JOB_GROUP);
CREATE INDEX IDX_QRTZ_FT_JG ON QRTZ_FIRED_TRIGGERS(SCHED_NAME,JOB_GROUP);
CREATE INDEX IDX_QRTZ_FT_T_G ON QRTZ_FIRED_TRIGGERS(SCHED_NAME,TRIGGER_NAME,TRIGGER_GROUP);
CREATE INDEX IDX_QRTZ_FT_TG ON QRTZ_FIRED_TRIGGERS(SCHED_NAME,TRIGGER_GROUP);

```
