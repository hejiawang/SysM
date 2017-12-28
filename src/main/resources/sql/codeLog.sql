#sql("paginate")
  from sysm_code_log
  #for( x : content)
    #(for.index == 0 ? "where" : "and") #(x.key) concat('%', #para(x.value), '%')
  #end
  ORDER BY date DESC
#end

#sql("findAll")
  select * from sysm_code_log ORDER BY date DESC
#end