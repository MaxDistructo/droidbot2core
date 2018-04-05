package maxdistructo.droidbot2.core.impl

import maxdistructo.droidbot2.core.obj.IHelp

object Help() : IHelp{

private val basicHelpHolder : List<String>
private val modHelpHolder : List<String>
private val adminHelpHolder : List<String>

override val basicHelp : List<String>{
  get() = basicHelpHolder
}

override val modHelp : List<String>{
  get() = modHelpHolder
}

override val adminHelp : List<String>{
  get() = adminHelpHolder
}

fun addBasicHelp(help : String){
  basicHelpHolder.add(help)
}

fun addModHelp(help : String){
  modHelpHolder.add(help)
}

fun addAdminHelp(help : String){
  adminHelpHolder.add(help)
}