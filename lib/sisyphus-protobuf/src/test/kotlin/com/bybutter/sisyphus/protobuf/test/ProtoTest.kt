package com.bybutter.sisyphus.protobuf.test

import com.bybutter.sisyphus.protobuf.MessagePatcher
import com.bybutter.sisyphus.protobuf.primitives.FileOptions
import com.bybutter.sisyphus.protobuf.primitives.Struct
import com.bybutter.sisyphus.protobuf.primitives.Value
import org.junit.jupiter.api.Assertions
import org.junit.jupiter.api.Test

class ProtoTest {
    @Test
    fun `base type marshal and unmarshal test`() {
        val raw = BaseTypeTest {
            stringValue = "test"
            int32Value = -1
            int64Value = -100
        }

        val unmarshlled = BaseTypeTest.parse(raw.toProto())
        Assertions.assertArrayEquals(raw.toProto(), unmarshlled.toProto())
    }

    @Test
    fun `nested message marshal and unmarshal test`() {
        val raw = NestedMessageTest {
            this.startValue = 1
            this.nestedMessageValue = NestedMessageTest.NestedMessage {
                this.int32Value = 3
            }
            this.endValue = 2
        }

        val data = raw.toProto()
        val unmarshlled = NestedMessageTest.parse(data)
        Assertions.assertArrayEquals(raw.toProto(), unmarshlled.toProto())
    }

    @Test
    fun `map message marshal and unmarshal test`() {
        val raw = MapMessageTest {
            this.startValue = 1
            this.messageMapValue += mapOf(
                "foo" to MapMessageTest.NestedMessage {
                    this.int32Value = 1
                },
                "bar" to MapMessageTest.NestedMessage {
                    this.int32Value = 2
                }
            )
            this.baseTypeMapValue += mapOf(1 to true, 2 to false, 3 to true)
            this.endValue = 2
        }

        val data = raw.toProto()
        val unmarshlled = MapMessageTest.parse(data)
        Assertions.assertArrayEquals(raw.toProto(), unmarshlled.toProto())
    }

    @Test
    fun `test packed field`() {
        val raw = PackedTest {
            this.values += listOf(1, 2, 3, 4, 5, 6)
        }

        val proto = PackedTest.parse(raw.toProto())
        Assertions.assertArrayEquals(proto.toProto(), raw.toProto())
    }

    @Test
    fun `test struct`() {
        val raw = Struct {
            fields += mapOf(
                "test" to Value {
                    kind = Value.Kind.StringValue("value")
                }
            )
        }
        Assertions.assertArrayEquals(raw.toProto(), Struct.parse(raw.toProto()).toProto())
    }

    @Test
    fun `extension test`() {
        val option = FileOptions {
            this.javaPackage = "test"
            this.myFileOption = "extension"
        }

        Assertions.assertArrayEquals(option.toProto(), FileOptions.parse(option.toProto()).toProto())
    }

    @Test
    fun `message patcher test`() {
        val patcher = MessagePatcher()
        patcher.add("start_value", "2")
        patcher.add("nested_message_value.int32_value", "3")
        patcher.add("end_value", "4")

        val raw = NestedMessageTest {
            patcher.applyTo(this)
        }

        Assertions.assertEquals(raw.startValue, 2)
        Assertions.assertEquals(raw.nestedMessageValue?.int32Value, 3)
        Assertions.assertEquals(raw.endValue, 4)
    }
}
