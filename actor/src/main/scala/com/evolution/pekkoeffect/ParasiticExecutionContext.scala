package com.evolution.pekkoeffect

import scala.concurrent.ExecutionContext

object ParasiticExecutionContext {
  def apply(): ExecutionContext = ExecutionContext.parasitic
}
