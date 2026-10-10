# NOTICE — 来源、版本与许可

NoAutoPause 是**从 [MineAgentAIO](https://github.com/MineAgent/MineAgentAIO) 抽出的单一功能模组**：
只保留「窗口失去焦点时不自动暂停」。代码按 **LGPL-3.0-only** 分发
（见 [`LICENSE`](LICENSE) / [`LICENSE.GPL-3.0`](LICENSE.GPL-3.0)）；文档按 **CC BY-NC-SA 4.0** 分发。

## 1. 代码来源（MineAgent，LGPL-3.0-only）

| 位置 | 来源 | 说明 |
| --- | --- | --- |
| `com.mineagent.noautopause.NoAutoPause` | MineAgentAIO 的 `com.mineagent.aio.NoAutoPause` | 逐行搬过来，只改了包名与日志 logger 名（`mineagentaio` → `noautopause`） |
| `com.mineagent.noautopause.mixin.MinecraftMixin` | MineAgentAIO 的 `com.mineagent.aio.mixin.MinecraftMixin` | 原文件里除 noautopause 外还调用了 `CraftJob#tick`（合成任务），这里**只保留** noautopause 那一行 |
| `com.mineagent.noautopause.NoAutoPauseMod` | 新写的入口点 | AIO 的入口点被 HTTP 服务占着，独立模组只需要一个报告版本的 `ClientModInitializer` |

## 2. 行为来源：noautopause 1.0.2（行为改编，非打包）

* 上游：<https://github.com/Armandukx/noautopause>（作者 Armandukx）
* 上游许可：上游仓库声明 **MIT**；分发的 `noautopause-1.0.2.jar` 里 `fabric.mod.json` 写的是
  **CC0-1.0**，同时附带一份未填写年份/作者的 MIT 许可模板。两者都是宽松许可，允许使用与再分发。
* 本仓库的处理：**没有打包上游 jar，也没有逐字复制其源码**，而是按其行为重新实现：每次客户端
  tick 检查一次，把 `Minecraft.options.pauseOnLostFocus` 置为 `false`（只做一次），效果与上游一致。
* 与上游的实现差异：上游用 Fabric API 的 `ClientTickEvents.END_CLIENT_TICK`；本模组**不依赖
  Fabric API**，改由 `MinecraftMixin` 注入 `Minecraft#tick` 的尾部调用，因此上游的 `fabric-api`
  依赖也一并去掉了。这一点与 MineAgentAIO 内的做法相同（见上一节）。
* 模组 id 与上游同为 `noautopause`：**两者不能同时安装**，否则 Fabric 会因 id 重复拒绝启动。
  本模组是上游的替代品（去掉 Fabric API 依赖的版本）。
* 致谢：Armandukx（noautopause）。
