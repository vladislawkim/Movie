package com.vladislawfox.base.presentation.functional

import kotlinx.coroutines.Job

class CompositeJob {
    private val jobs = hashMapOf<String, Job>()

    fun add(job: Job, key: String = job.hashCode().toString()) = jobs.put(key, job)

    fun cancel(job: Job) = jobs[job.hashCode().toString()]?.cancel()

    fun cancel() = jobs.forEach { it.value.cancel() }
}

operator fun CompositeJob.plusAssign(job: Job) {
    add(job)
}