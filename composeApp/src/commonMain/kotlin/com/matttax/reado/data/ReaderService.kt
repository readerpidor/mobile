package com.matttax.reado.data

import com.matttax.reado.data.model.ask.AskRequest
import com.matttax.reado.data.model.ask.AskResponse
import com.matttax.reado.data.model.get_history.GetHistoryResponse
import com.matttax.reado.data.model.get_next_parts.GetNextPartsRequest
import com.matttax.reado.data.model.get_next_parts.GetNextPartsResponse
import com.matttax.reado.data.model.get_part_by_anchor.GetPartByAnchorRequest
import com.matttax.reado.data.model.get_part_by_anchor.GetPartByAnchorResponse
import com.matttax.reado.data.model.get_qa_history.GetQAHistoryRequest
import com.matttax.reado.data.model.get_qa_history.GetQAHistoryResponse
import com.matttax.reado.data.model.process.ProcessRequest
import com.matttax.reado.data.model.process.ProcessResponse

interface ReaderService {
  suspend fun process(request: ProcessRequest): ProcessResponse
  suspend fun getNextParts(request: GetNextPartsRequest): GetNextPartsResponse
  suspend fun getHistory(): GetHistoryResponse
  suspend fun getQAHistory(request: GetQAHistoryRequest): GetQAHistoryResponse
  suspend fun ask(request: AskRequest): AskResponse
  suspend fun getPartByAnchor(request: GetPartByAnchorRequest): GetPartByAnchorResponse
}
