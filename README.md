# Demoiselle Scheduler

## Configuração do Projeto

    O componente funciona em métodos das camadas de Negócio e de Persistência, ou seja, em métodos de classes
    anotadas com @BusinessController e @PersistenceControler.

### O primeiro passo é importar o componente no pom.xml


<dependency>
    <groupId>br.gov.frameworkdemoiselle.component</groupId>
    <artifactId>demoiselle-scheduler-quartz</artifactId>
    <version>1.0.0-SNAPSHOT</version>
</dependency>

### Depois é só anotar o método que você deseja que seja executado com @Schedule.



@Schedule(cron = "0 0/1 * * * ?")
public void insertAgendado() {
    insert(new Bookmark("Insert Agendado", "" + System.currentTimeMillis()));
}


### O parâmetro cron segue o padrão unix, que pode ser consultado nos seguintes links:

    http://pt.wikipedia.org/wiki/Cron

    http://en.wikipedia.org/wiki/Cron
