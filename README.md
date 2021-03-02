# itask

O sistema utiliza identificador único universal UUID como chave primária para a persistência dos objetos.
O banco de dados utilizado é o mysql, mais detalhes em src\main\resources\application.properties

TarefaRestController.java

	"localhost:8080/tarefas" |content-type : json  |
		put "/{id}" editar (body: json)
		post "/" criar (body: json)
		delete "/{id}" excluir
		get "" → retorna todas as tarefas ordenadas por data de criação
		get "/xls" → retorna arquivo excel
       
ConsultaRestController.java


	"localhost:8080/tarefas/consulta" |content-type : json  |
	esta classe retorna consultas pagináveis do tipo List<Tarefa> pesquisadas por data de início, 
	reponsável e status.
	Em todos os casos deve inserir os parâmetros:
                     → "tamanhoPaginas" = a quantidade de itens que deseja obter por consulta
                     → "numeroPagina" = o índice da página que deseja obter (iniciando em 0)
                     → "ordem" = inserir a preferencia de ordenação asc para crescente e desc para
		     decrescente e a api fará a ordenação de acordo com o critério de busca escolhido.
                     → o próximo parâmetro será de acordo com o critério de busca escolhido:
		     
    						get "/data" inserir parametro "dataInicio"
    						get "/responsavel" inserir parametro "responsavel"
   						get "/responsavel" inserir parametro "status"
    
