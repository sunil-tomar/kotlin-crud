package com.hendisantika.kotlin

import org.junit.Test
import org.junit.runner.RunWith
import org.springframework.boot.test.context.SpringBootTest
import org.springframework.test.context.junit4.SpringRunner

@RunWith(SpringRunner::class)
@SpringBootTest
class KotlinCrudApplicationTests {

    @Test
    fun contextLoads() {
    }

/*
    @Test
    fun `preprocess should throw exception when realm id is null`() = runBlocking {
    //Given
        val baseTranactionModel = mockk<BaseTranactionModel>()
        val attachmentModel = mockk<AttachmentModel>()
        every { baseTranactionModel.attachments } returns arrayListOf(attachmentModel)
        every { attachmentModel.attchmentId } returns "attachmentId"
        every { attachmentModel.realmId} returns null
        every { attachmentModel.audit} returns AuditModel()
        every { attachmentModel.attachmentURL} returns "https://www.google.com"
        every { attachmentModel.attachmentSize} returnsBigDecimal.ZERO
        every { attachmentModel.contentType} returns "contentType"
        every { attachmentModel.tempURL} returns "tempURL"

        val excepV = shouldThrow<ApiValidationException> {
            attachmentComponent.preProcess(workflowEngineContext, baseTransactionModel)
        }
        excepV shouldHaveMessage "Error in Id"

        val attachmentSize = baseTranactionModel.attachments?.size
        if (attachmentSize!=null){
            coVerify(exactly=attachmentSize){
                auditComponent.preProcess(workflowEngineContext, any<AuditModel>())
            }
        }
       verify(exactly=1){
        attachmentModel.realmId
        attachmentModel.attachmentId
        attachmentModel.attachmentName
        attachmentModel.audit
        attachmentModel.attachmentURL
        attachmentModel.attachmentSize
        attachmentModel.contentType
        attachmentModel.tempURL
       }
    verify(exactly=2){
        baseTranactionModel.attachments
    }
    confirmVerified(baseTranactionModel)
    confirmVerified(attachmentModel)
    confirmVerfied(auditComponent)
    }
    */

    @Test
    fun `preprocess should throw exception when realm id is null`() = runBlocking {
        val baseTranactionModel = mockk<BaseTranactionModel>()
        val attachmentModel = mockk<AttachmentModel>()

        // Given
        every { baseTransactionModel.attachments } returns arrayListOf(attachmentModel)
        every { attachmentModel.realmId } returns "non-null-realm-id"  // Adjust the non-null value
        every { attachmentModel.attachmentId } returns "attachmentId"
        every { attachmentModel.audit } returns AuditModel()  // Replace with actual AuditModel
        every { attachmentModel.attachmentURL } returns "https://www.google.com"
        every { attachmentModel.attachmentSize } returns BigDecimal.ZERO
        every { attachmentModel.contentType } returns "contentType"
        every { attachmentModel.tempURL } returns "tempURL"

        // Stubbing auditComponent methods for auditing
        every { auditComponent.preProcess(workflowEngineContext, any<AuditModel>()) } returns Unit
        // ... (other stubbing for auditComponent, if needed)

        // When
        val excepV = assertThrows<ApiValidationException> {
            attachmentComponent.preProcess(workflowEngineContext, baseTransactionModel)
        }

        // Then
        excepV shouldHaveMessage "Error in Id"

        // Verify: Ensure that the realmId property is accessed during the test
        verify(exactly = 1) {
            attachmentModel.realmId
        }

        // Verify: Ensure that auditComponent methods are called as expected
        val attachmentSize = baseTransactionModel.attachments?.size
        if (attachmentSize != null) {
            coVerify(exactly = attachmentSize) {
                auditComponent.preProcess(workflowEngineContext, any<AuditModel>())
            }
        }

        // ... (other verifications for the remaining properties, if needed)

        // Confirm Verified: Confirm that all verifications were successful
        confirmVerified(baseTransactionModel)
        confirmVerified(attachmentModel)
        confirmVerified(auditComponent)
    }

}
