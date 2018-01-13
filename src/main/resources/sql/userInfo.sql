#sql("paginate")
  from sysm_user_info
  #for( x : content)
    #(for.index == 0 ? "where" : "or") #(x.key) concat('%', #para(x.value), '%')
  #end
  ORDER BY updateTime DESC
#end